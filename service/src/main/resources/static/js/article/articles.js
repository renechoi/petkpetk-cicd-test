function sortArticles() {
    const sortValue = $('input[name="sort"]:checked').val();
    $.ajax({
        type: 'GET', url: generateSortUrl(sortValue), dataType: 'html', success: function (data) {
            const articles = $(data).find('#articleList').html();
            $('#articleList').html(articles);
        }
    });
}


$("#category-apply").on("click", function () {
    const categories = $('input[type="checkbox"][name="category"]:checked')
        .map(function () {
            return this.value;
        }).get();

    const searchValue = categories.join(",");
    const searchType = document.getElementById("category-search-type");

    $.ajax({
        type: "GET",
        url: generateSearchUrl(searchType.value, searchValue),
        dataType: "html",
        success: function (data) {
            const articles = $(data).find('#articleList').html();
            $('#articleList').html(articles);
        }
    });
});


function generateSortUrl(sortValue) {
    const baseUrl = window.location.href.split('?')[0];
    const queryParam = `sort=${sortValue}`;

    return window.location.href.includes("?") ? `${baseUrl}?${queryParam}` : `${window.location.href}?${queryParam}`;
}


function generateSearchUrl(searchType, searchValue) {
    const baseUrl = window.location.href.split('?')[0];
    const queryParam = `searchType=${searchType}` + `&searchValue=${searchValue}`;

    return window.location.href.includes("?") ? `${baseUrl}?${queryParam}` : `${window.location.href}?${queryParam}`;
}

var num = 1;

function getMoreArticles() {
    $.ajax({
        url: "/api/articles",
        type: "post",
        data: {
            page: num,
            searchType: $("#searchType").val(),
            searchValue: $("#searchValue").val()
        },
        dataType: "json",
        success: function (result) {
            console.log("가져옴");
            var article = result['article'];
            var totalCount = result['totalCount'];


            for (let i = 0; i < article.length; i++) {
                const articleDateTime = moment(article[i].createdAt);
                const articleMinutes = moment().diff(articleDateTime, 'minutes');
                const articleHours = moment().diff(articleDateTime, 'hours');
                const articleDays = moment().diff(articleDateTime, 'days');

                let dateDiffText = '';

                if (articleMinutes < 1) {
                    // 1분 이내인 경우
                    const articleSeconds = moment().diff(articleDateTime, 'seconds');
                    dateDiffText = `${articleSeconds}초 전`;
                }else if (articleHours < 1) {
                    // 1시간 이내인 경우
                    const articleMinutes = moment().diff(articleDateTime, 'minutes');
                    dateDiffText = `${articleMinutes}분 전`;
                } else if (articleDays < 1) {
                    // 24시간 이내인 경우
                    dateDiffText = `${articleHours}시간 전`;
                } else if (articleDays === 1) {
                    // 1일 전인 경우
                    dateDiffText = '1일 전';
                } else if (articleDays <= 3) {
                    // 2일 전 ~ 3일 전인 경우
                    dateDiffText = `${articleDays}일 전`;
                } else if (articleDays < 365) {
                    // 1년 이내인 경우
                    dateDiffText = `${articleDateTime.format('M월 D일')}`;
                } else {
                    // 1년 이상인 경우
                    dateDiffText = `${articleDateTime.format('YYYY년 M월 D일')}`;
                }
                let createdAt = dateDiffText;

                if (i % 3 == 0) {

                    var content = "";
                    content += "<div><div class='oneArticleBox'><a href='/community/articles/" + article[i].id + "'>";

                    if (article[i].rawImages != null) {
                        content += "<img class='articleFirstImage' src='" + article[i].rawImages[0] + "'>";
                    }

                    content += "<div class='articleContentBox'>" +
                        "<div>" +
                        "<div class='userInfoBox'>" +
                        "<div class='userNickName'>닉네임</div>" +
                        "<div class='registerTime'>" + createdAt + "</div>" +
                        "</div>" +
                        "<div style='display: flex; justify-content: space-between;'>" +
                        "<div class='articleTitle'>" + article[i].title + "</div>" +
                        "<div class='articleLikes'>♥546</div>" +
                        "</div>" +
                        "<div class='articleContent'>" +
                        "<div class='contentDetail'>" + article[i].content + "</div>" +
                        "</div>" +
                        "</div>";

                    if (article[i].hashtags != null) {
                        content += "<div class='hashTagBox'>";
                        for (let j = 0; j < article[i].hashtags.length; j++) {
                            content += "<div class='hashTaging'><span class='hashTag'>" + article[i].hashtags[j] + "</span></div>";
                        }
                        content += "</div>";
                    }
                    if (article[i].hit == null) {
                        article[i].hit = 0;
                    }

                    content += "<div class='articleHit'>조회수 " + article[i].hit + "</div>";

                    content += "</div></a></div></div>";


                    $("#articleLine1").append(content);
                } else if (i % 3 == 1) {
                    var content = "";
                    content += "<div><div class='oneArticleBox'><a href='/community/articles/" + article[i].id + "'>";

                    if (article[i].rawImages != null) {
                        content += "<img class='articleFirstImage' src='" + article[i].rawImages[0] + "'>";
                    }

                    content += "<div class='articleContentBox'>" +
                        "<div>" +
                        "<div class='userInfoBox'>" +
                        "<div class='userNickName'>닉네임</div>" +
                        "<div class='registerTime'>" + createdAt + "</div>" +
                        "</div>" +
                        "<div style='display: flex; justify-content: space-between;'>" +
                        "<div class='articleTitle'>" + article[i].title + "</div>" +
                        "<div class='articleLikes'>♥546</div>" +
                        "</div>" +
                        "<div class='articleContent'>" +
                        "<div class='contentDetail'>" + article[i].content + "</div>" +
                        "</div>" +
                        "</div>";

                    if (article[i].hashtags != null) {
                        content += "<div class='hashTagBox'>";
                        for (let j = 0; j < article[i].hashtags.length; j++) {
                            content += "<div class='hashTaging'><span class='hashTag'>" + article[i].hashtags[j] + "</span></div>";
                        }
                        content += "</div>";
                    }
                    if (article[i].hit == null) {
                        article[i].hit = 0;
                    }

                    content += "<div class='articleHit'>조회수 " + article[i].hit + "</div>";

                    content += "</div></a></div></div>";

                    $("#articleLine2").append(content);

                } else if (i % 3 == 2) {
                    var content = "";
                    content += "<div><div class='oneArticleBox'><a href='/community/articles/" + article[i].id + "'>";

                    if (article[i].rawImages != null) {
                        content += "<img class='articleFirstImage' src='" + article[i].rawImages[0] + "'>";
                    }

                    content += "<div class='articleContentBox'>" +
                        "<div>" +
                        "<div class='userInfoBox'>" +
                        "<div class='userNickName'>닉네임</div>" +
                        "<div class='registerTime'>" + createdAt + "</div>" +
                        "</div>" +
                        "<div style='display: flex; justify-content: space-between;'>" +
                        "<div class='articleTitle'>" + article[i].title + "</div>" +
                        "<div class='articleLikes'>♥546</div>" +
                        "</div>" +
                        "<div class='articleContent'>" +
                        "<div class='contentDetail'>" + article[i].content + "</div>" +
                        "</div>" +
                        "</div>";

                    if (article[i].hashtags != null) {
                        content += "<div class='hashTagBox'>";
                        for (let j = 0; j < article[i].hashtags.length; j++) {
                            content += "<div class='hashTaging'><span class='hashTag'>" + article[i].hashtags[j] + "</span></div>";
                        }
                        content += "</div>";
                    }
                    if (article[i].hit == null) {
                        article[i].hit = 0;
                    }

                    content += "<div class='articleHit'>조회수 " + article[i].hit + "</div>";

                    content += "</div></a></div></div>";

                    $("#articleLine3").append(content);
                }
            }
            showMoreBtn(totalCount);
        }
    });
    num++;
}

function showPost() {
    var postCat = document.getElementById("postCat");

    if (postCat.style.display == "none") {
        postCat.style.display = "block";
    } else {
        postCat.style.display = "none";
    }
}

function showMoreBtn(totalCount) {
    var oneArticleBoxs = document.getElementsByClassName("oneArticleBox");
    var getMoreBtn = document.getElementById("getMoreBtn");

    if (oneArticleBoxs.length < totalCount) {
        getMoreBtn.style.display = "block";
    } else {
        getMoreBtn.style.display = "none";
    }

    if (oneArticleBoxs.length < 12) {
        getMoreBtn.style.display = "none";
    }

}

showMoreBtn($("#totalCount").val());

var articlesList = document.getElementsByClassName("articles");

for (let i = 0; i < articlesList.length; i++) {
    console.log(articlesList.item(i).value);

    const articleDateTime = moment(articlesList[i].value);
    const articleMinutes = moment().diff(articleDateTime, 'minutes');
    const articleHours = moment().diff(articleDateTime, 'hours');
    const articleDays = moment().diff(articleDateTime, 'days');

    let dateDiffText = '';

    if (articleMinutes < 1) {
        // 1분 이내인 경우
        const articleSeconds = moment().diff(articleDateTime, 'seconds');
        dateDiffText = `${articleSeconds}초 전`;
    }else if (articleHours < 1) {
        // 1시간 이내인 경우
        const articleMinutes = moment().diff(articleDateTime, 'minutes');
        dateDiffText = `${articleMinutes}분 전`;
    } else if (articleDays < 1) {
        // 24시간 이내인 경우
        dateDiffText = `${articleHours}시간 전`;
    } else if (articleDays === 1) {
        // 1일 전인 경우
        dateDiffText = '1일 전';
    } else if (articleDays <= 3) {
        // 2일 전 ~ 3일 전인 경우
        dateDiffText = `${articleDays}일 전`;
    } else if (articleDays < 365) {
        // 1년 이내인 경우
        dateDiffText = `${articleDateTime.format('M월 D일')}`;
    } else {
        // 1년 이상인 경우
        dateDiffText = `${articleDateTime.format('YYYY년 M월 D일')}`;
    }

    $('#createdAt' + (i + 1)).text(dateDiffText);
}




