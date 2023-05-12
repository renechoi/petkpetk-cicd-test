package com.petkpetk.service.config.trace;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ThreadLocalLogTrace implements LogTrace {

	private static final String START_PREFIX = "-->";
	private static final String COMPLETE_PREFIX = "<--";
	private static final String EXCEPTION_PREFIX = "<X-";

	private ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<>();

	@Override
	public TraceStatus begin(String message) {
		syncTraceId();
		TraceId traceId = getCurrentTraceId();
		Long startTimeMs = System.currentTimeMillis();
		log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);

		return new TraceStatus(traceId, startTimeMs, message);
	}

	@Override
	public void end(Object result, TraceStatus status) {
		complete(result, status, null);
	}

	@Override
	public void exception(Object result, TraceStatus status, Exception exception) {
		complete(result, status, exception);
	}

	private void complete(Object result, TraceStatus status, Exception exception) {
		long resultTimeMs = System.currentTimeMillis() - status.getStartTimeMs();
		TraceId traceId = status.getTraceId();
		if (exception == null) {
			log.info("[{}] {}{} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()),
				status.getMessage(), resultTimeMs);
			log.info("[{}] Process Result {}", traceId.getId(), result);
		} else {
			log.info("[{}] {}{} time={}ms ex={}", traceId.getId(), addSpace(EXCEPTION_PREFIX, traceId.getLevel()),
				status.getMessage(), resultTimeMs, exception.toString());
		}

		releaseTraceId();
	}

	private void syncTraceId() {
		TraceId traceId = getCurrentTraceId();
		if (traceId == null) {
			traceIdHolder.set(new TraceId());
		} else {
			traceIdHolder.set(traceId.createNextId());
		}
	}

	private void releaseTraceId() {
		TraceId currentTraceId = getCurrentTraceId();
		if (isFirstLevelTrace(currentTraceId)) {
			removeTraceId();
			return;
		}
		setPreviousTraceId(currentTraceId);
	}

	private TraceId getCurrentTraceId() {
		return traceIdHolder.get();
	}

	private boolean isFirstLevelTrace(TraceId traceId) {
		return traceId.isFirstLevel();
	}

	private void removeTraceId() {
		traceIdHolder.remove();
	}

	private void setPreviousTraceId(TraceId currentTraceId) {
		TraceId previousTraceId = currentTraceId.createPreviousId();
		traceIdHolder.set(previousTraceId);
	}

	private static String addSpace(String prefix, int level) {
		return IntStream.range(0, level)
			.mapToObj(i -> (i == level - 1) ? "|" + prefix : "|   ")
			.collect(Collectors.joining());
	}
}
