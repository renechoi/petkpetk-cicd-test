
INSERT INTO user_account
(created_at,created_by,modified_at,modified_by,deleted_yn,email, password, name, nickname, zip_code, address1, address2, address_etc, oauth2_provider_info, roles, phone_number, business_name, business_number)
VALUES
(now(),'user', now(),'user','N','user@user.com', '{noop}1234', '김', '123', '12345', 'Gangnam', 'South Korea', '2345',  null, 'USER', null,null,null),
(now(),'seller', now(),'seller','N','seller@seller.com', '{noop}1234', '이', '345', '3456', 'Gangnam', 'South Korea', '345',  null, 'SELLER','010-1234-1234','페크페크','123-12345-123'),
(now(),'admin', now(),'user','N','admin@admin.com', '{noop}1234', '최', '567', '6574', 'Gangnam', 'South Korea', '4356',  null, 'ADMIN',null,null,null),
(now(), 'seller', now(), 'seller', 'N', 'seller5@seller5.com', '{noop}1234', '김철수', 'chulsoo', '12345', 'Gangnam', 'South Korea', '2345', null, 'SELLER', '010-1234-5678', '김철수 주식회사', '123-45-67890'),
(now(), 'user', now(), 'user', 'N', 'user2@user.com', '{noop}1234', '박민수', 'minsu', '23456', 'Seoul', 'South Korea', '3456', null, 'USER', '010-2345-6789', '박민수 주식회사', '234-56-78901'),
(now(), 'user', now(), 'user', 'N', 'user3@user.com', '{noop}1234', '이영희', 'younghee', '34567', 'Busan', 'South Korea', '4567', null, 'USER', '010-3456-7890', '이영희 주식회사', '345-67-89012'),
(now(), 'seller', now(), 'seller', 'N', 'seller1@seller.com', '{noop}1234', '홍길동', 'gildong', '45678', 'Gangnam', 'South Korea', '5678', null, 'SELLER', '010-4567-8901', '홍길동 주식회사', '456-78-90123'),
(now(), 'seller', now(), 'seller', 'N', 'seller2@seller.com', '{noop}1234', '임꺽정', 'kkakjong', '56789', 'Seoul', 'South Korea', '6789', null, 'SELLER', '010-5678-9012', '임꺽정 주식회사', '567-89-01234'),
(now(), 'seller', now(), 'seller', 'N', 'seller3@seller.com', '{noop}1234', '신사임당', 'saimdang', '67890', 'Busan', 'South Korea', '7890', null, 'SELLER', '010-6789-0123', '신사임당 주식회사', '678-90-12345'),
(now(), 'user', now(), 'user', 'N', 'user4@user.com', '{noop}1234', '김영수', 'youngsoo', '78901', 'Gangnam', 'South Korea', '8901', null, 'USER', '010-7890-1234', '김영수 주식회사', '789-01-23456');


insert into user_account (deleted_yn, created_at, modified_at, created_by, modified_by, address1, address2, address_etc, zip_code, email, name, nickname,  password, oauth2_provider_info, roles, phone_number, business_name, business_number) values ('N', '2022-05-06', '2022-06-20', 'Fidelio Kellie', 'Adrian Thumann', '6 Superior Street', 'Room 1872', 'Mauv', '401', 'sguerreau2@sbwire.com', 'Silvanus Guerreau', 'sguerreau2',  '{noop}1234', null, 'USER', null, null, null);
insert into user_account (deleted_yn, created_at, modified_at, created_by, modified_by, address1, address2, address_etc, zip_code, email, name, nickname,  password, oauth2_provider_info, roles, phone_number, business_name, business_number) values ('N', '2022-12-27', '2022-12-27', 'Sinclare Amsden', 'Avram Pierton', '5872 Veith Court', 'PO Box 16601', 'Mauv', '2', 'gcosby3@bbb.org', 'Gustie Cosby', 'gcosby3',  '{noop}1234', null, 'ADMIN', null, null, null);
insert into user_account (deleted_yn, created_at, modified_at, created_by, modified_by, address1, address2, address_etc, zip_code, email, name, nickname,  password, oauth2_provider_info, roles, phone_number, business_name, business_number) values ('N', '2023-02-22', '2022-11-25', 'Frederico Masterson', 'Rhett McKnish', '685 Fallview Drive', '19th Floor', 'Fuscia', '952', 'lcranmer1@intel.com', 'Lief Cranmer', 'lcranmer1', '{noop}1234', null, 'USER', null, null, null);
insert into user_account (deleted_yn, created_at, modified_at, created_by, modified_by, address1, address2, address_etc, zip_code, email, name, nickname,  password, oauth2_provider_info, roles, phone_number, business_name, business_number) values ('N', '2022-12-07', '2022-12-29', 'Flossie Rule', 'Flora Barsham', '78 Longview Lane', 'PO Box 39856', 'Violet', '3', 'tcarley0@mapy.cz', 'Tabor Carley', 'tcarley0',  '{noop}1234', null, 'USER', null, null, null);
insert into user_account (deleted_yn, created_at, modified_at, created_by, modified_by, address1, address2, address_etc, zip_code, email, name, nickname,  password, oauth2_provider_info, roles, phone_number, business_name, business_number) values ('N', '2022-04-24', '2023-01-03', 'Calvin Willars', 'Lauren Arsmith', '388 Manufacturers Pass', 'Apt 497', 'Goldenrod', '2616', 'epredohl4@vk.com', 'Esta Predohl', 'epredohl4',  '{noop}1234', null, 'USER', null, null, null);
insert into user_account (deleted_yn, created_at, modified_at, created_by, modified_by, address1, address2, address_etc, zip_code, email, name, nickname,  password, oauth2_provider_info, roles, phone_number, business_name, business_number) values ('N', '2023-03-16', '2023-02-07', 'Julie Ensor', 'Lalo Blachford', '651 Northview Terrace', 'Suite 25', 'Purple', '469', 'kkaspar5@marketwatch.com', 'Kai Kaspar', 'kkaspar5',  '{noop}1234', null, 'ADMIN', null, null, null);
insert into user_account (deleted_yn, created_at, modified_at, created_by, modified_by, address1, address2, address_etc, zip_code, email, name, nickname,  password, oauth2_provider_info, roles, phone_number, business_name, business_number) values ('N', '2022-06-29', '2022-12-24', 'Jody Caves', 'Gilberta Driscoll', '43784 Bluejay Hill', 'PO Box 6729', 'Green', '6909', 'elempenny6@myspace.com', 'Eddi Lempenny', 'elempenny6', '{noop}1234', 'NAVER', 'ADMIN', null, null, null);
insert into user_account (deleted_yn, created_at, modified_at, created_by, modified_by, address1, address2, address_etc, zip_code, email, name, nickname,  password, oauth2_provider_info, roles, phone_number, business_name, business_number) values ('N', '2022-08-10', '2022-07-26', 'Andy Coote', 'Arni Duffell', '57142 3rd Crossing', '20th Floor', 'Turquoise', '636', 'csyder7@gravatar.com', 'Cordi Syder', 'csyder7', '{noop}1234', 'GOOGLE', 'SELLER', null, null, null);
insert into user_account (deleted_yn, created_at, modified_at, created_by, modified_by, address1, address2, address_etc, zip_code, email, name, nickname,  password, oauth2_provider_info, roles, phone_number, business_name, business_number) values ('N', '2022-06-02', '2023-01-03', 'Tara Doyland', 'Kissie Bome', '16749 Schmedeman Crossing', 'PO Box 46633', 'Yellow', '9063', 'grustich8@yelp.com', 'Gae Rustich', 'grustich8', '{noop}1234', 'KAKAO', 'SELLER', null, null, null);
insert into user_account (deleted_yn, created_at, modified_at, created_by, modified_by, address1, address2, address_etc, zip_code, email, name, nickname,  password, oauth2_provider_info, roles, phone_number, business_name, business_number) values ('N', '2022-06-07', '2022-09-03', 'Hayward Shuttell', 'Natalee Haile', '7 Coleman Parkway', 'Apt 1477', 'Crimson', '8164', 'flaxson9@pinterest.com', 'Filmer Laxson', 'flaxson9', '{noop}1234', 'NAVER', 'ADMIN', null, null, null);
insert into user_account (deleted_yn, created_at, modified_at, created_by, modified_by, address1, address2, address_etc, zip_code, email, name, nickname,  password, oauth2_provider_info, roles, phone_number, business_name, business_number) values ('N', '2023-04-04', '2022-11-30', 'Helena Pavlovsky', 'Felicity Castillon', '11 Waubesa Street', 'Suite 32', 'Teal', '98', 'aclemmeya@forbes.com', 'Ardeen Clemmey', 'aclemmeya', '{noop}1234', 'NAVER', 'USER', null, null, null);
insert into user_account (deleted_yn, created_at, modified_at, created_by, modified_by, address1, address2, address_etc, zip_code, email, name, nickname,  password, oauth2_provider_info, roles, phone_number, business_name, business_number) values ('N', '2022-06-16', '2022-08-29', 'Dorita Blunsum', 'Tyrone Cuthbert', '35 Stephen Street', 'PO Box 48961', 'Fuscia', '6235', 'lsollisb@hhs.gov', 'Luis Sollis', 'lsollisb', '{noop}1234', 'KAKAO', 'USER', null, null, null);
insert into user_account (deleted_yn, created_at, modified_at, created_by, modified_by, address1, address2, address_etc, zip_code, email, name, nickname,  password, oauth2_provider_info, roles, phone_number, business_name, business_number) values ('N', '2022-07-14', '2022-07-16', 'Karil Besque', 'Lib Ottey', '481 Melby Court', 'Apt 868', 'Violet', '7435', 'asiddaleyc@mayoclinic.com', 'Aimil Siddaley', 'asiddaleyc',  '{noop}1234', 'GOOGLE', 'USER', null, null, null);
insert into user_account (deleted_yn, created_at, modified_at, created_by, modified_by, address1, address2, address_etc, zip_code, email, name, nickname,  password, oauth2_provider_info, roles, phone_number, business_name, business_number) values ('N', '2022-11-11', '2022-06-27', 'Marthe Hirjak', 'Titos Jonke', '257 Redwing Alley', 'Room 1851', 'Teal', '47', 'tgaitskilld@booking.com', 'Tresa Gaitskill', 'tgaitskilld',  '{noop}1234', 'NAVER', 'ADMIN', null, null, null);
insert into user_account (deleted_yn, created_at, modified_at, created_by, modified_by, address1, address2, address_etc, zip_code, email, name, nickname,  password, oauth2_provider_info, roles, phone_number, business_name, business_number) values ('N', '2022-11-26', '2023-02-04', 'Dot Forcade', 'Gray Robion', '040 Thierer Center', 'PO Box 84240', 'Green', '730', 'kecclese@canalblog.com', 'Keeley Eccles', 'kecclese',  '{noop}1234', 'NAVER', 'SELLER', null, null, null);
insert into user_account (deleted_yn, created_at, modified_at, created_by, modified_by, address1, address2, address_etc, zip_code, email, name, nickname,  password, oauth2_provider_info, roles, phone_number, business_name, business_number) values ('N', '2022-10-13', '2022-05-19', 'Avivah Divers', 'Corrie Eglington', '0 Norway Maple Lane', '7th Floor', 'Turquoise', '027', 'lpeedellf@toplist.cz', 'Lemmy Peedell', 'lpeedellf', '{noop}1234', 'KAKAO', 'ADMIN', null, null, null);
insert into user_account (deleted_yn, created_at, modified_at, created_by, modified_by, address1, address2, address_etc, zip_code, email, name, nickname,  password, oauth2_provider_info, roles, phone_number, business_name, business_number) values ('N', '2022-04-24', '2022-10-19', 'Erl Antonias', 'Jewel Molan', '16179 Reinke Center', 'PO Box 44937', 'Turquoise', '321', 'gdeekg@sciencedaily.com', 'Gordon Deek', 'gdeekg',  '{noop}1234', 'KAKAO', 'USER', null, null, null);
insert into user_account (deleted_yn, created_at, modified_at, created_by, modified_by, address1, address2, address_etc, zip_code, email, name, nickname,  password, oauth2_provider_info, roles, phone_number, business_name, business_number) values ('N', '2022-11-07', '2023-03-08', 'Alysia Pargeter', 'Rivkah Griffiths', '5 Dennis Plaza', 'Room 1835', 'Green', '5580', 'bellicotth@photobucket.com', 'Brittani Ellicott', 'bellicotth', '{noop}1234', 'NAVER', 'ADMIN', null, null, null);
insert into user_account (deleted_yn, created_at, modified_at, created_by, modified_by, address1, address2, address_etc, zip_code, email, name, nickname,  password, oauth2_provider_info, roles, phone_number, business_name, business_number) values ('N', '2022-10-16', '2023-02-08', 'Eleonore Das', 'Liesa De Pero', '1 Brickson Park Street', '3rd Floor', 'Blue', '7919', 'cpeeki@mac.com', 'Clarita Peek', 'cpeeki',  '{noop}1234', 'GOOGLE', 'ADMIN', null, null, null);
insert into user_account (deleted_yn, created_at, modified_at, created_by, modified_by, address1, address2, address_etc, zip_code, email, name, nickname,  password, oauth2_provider_info, roles, phone_number, business_name, business_number) values ('N', '2022-07-07', '2022-12-01', 'Adham Rushbrooke', 'Vivianna Blankhorn', '0385 Southridge Circle', 'Suite 81', 'Aquamarine', '76529', 'alandmanj@mail.ru', 'Arv Landman', 'alandmanj', '{noop}1234', 'KAKAO', 'USER', null, null, null);
insert into user_account (deleted_yn, created_at, modified_at, created_by, modified_by, address1, address2, address_etc, zip_code, email, name, nickname,  password, oauth2_provider_info, roles, phone_number, business_name, business_number) values ('N', '2023-02-27', '2023-03-24', 'Chiarra Bore', 'Grazia Sang', '77 Burrows Center', 'Room 1775', 'Khaki', '564', 'lcawsek@edublogs.org', 'Lorilee Cawse', 'lcawsek',  '{noop}1234', 'NAVER', 'SELLER', null, null, null);
insert into user_account (deleted_yn, created_at, modified_at, created_by, modified_by, address1, address2, address_etc, zip_code, email, name, nickname,  password, oauth2_provider_info, roles, phone_number, business_name, business_number) values ('N', '2022-07-12', '2023-01-13', 'Mandy Vannah', 'Burnaby Mulder', '628 Miller Street', 'Apt 397', 'Green', '4305', 'mbattsl@bing.com', 'Marcelle Batts', 'mbattsl', '{noop}1234', 'GOOGLE', 'SELLER', null, null, null);
insert into user_account (deleted_yn, created_at, modified_at, created_by, modified_by, address1, address2, address_etc, zip_code, email, name, nickname,  password, oauth2_provider_info, roles, phone_number, business_name, business_number) values ('N', '2022-12-06', '2023-03-08', 'Danielle Armit', 'Kaleena Houldey', '421 Namekagon Park', 'Apt 296', 'Khaki', '9906', 'nrosenvasserm@howstuffworks.com', 'Noam Rosenvasser', 'nrosenvasserm',  '{noop}1234', 'NAVER', 'ADMIN', null, null, null);
insert into user_account (deleted_yn, created_at, modified_at, created_by, modified_by, address1, address2, address_etc, zip_code, email, name, nickname,  password, oauth2_provider_info, roles, phone_number, business_name, business_number) values ('N', '2022-07-03', '2022-06-01', 'Kennedy Brashaw', 'Joscelin Gillow', '733 Waywood Road', '12th Floor', 'Khaki', '43928', 'vmorphetn@yellowpages.com', 'Vin Morphet', 'vmorphetn', '{noop}1234', 'KAKAO', 'ADMIN', null, null, null);
insert into user_account (deleted_yn, created_at, modified_at, created_by, modified_by, address1, address2, address_etc, zip_code, email, name, nickname,  password, oauth2_provider_info, roles, phone_number, business_name, business_number) values ('N', '2022-08-11', '2022-08-16', 'Valerie Chatelet', 'Christy Panting', '788 Dawn Way', 'PO Box 60449', 'Pink', '3202', 'dnorawayo@reuters.com', 'Debee Noraway', 'dnorawayo', '{noop}1234', 'GOOGLE', 'ADMIN', null, null, null);
insert into user_account (deleted_yn, created_at, modified_at, created_by, modified_by, address1, address2, address_etc, zip_code, email, name, nickname,  password, oauth2_provider_info, roles, phone_number, business_name, business_number) values ('N', '2022-11-25', '2022-09-11', 'Raimund Levine', 'Christiano Heffernon', '9644 Maple Crossing', 'PO Box 77030', 'Maroon', '9', 'tcicculip@cyberchimps.com', 'Terrijo Cicculi', 'tcicculip',  '{noop}1234', 'GOOGLE', 'USER', null, null, null);
insert into user_account (deleted_yn, created_at, modified_at, created_by, modified_by, address1, address2, address_etc, zip_code, email, name, nickname,  password, oauth2_provider_info, roles, phone_number, business_name, business_number) values ('N', '2023-02-27', '2023-03-25', 'Laney Vedyasov', 'Isabel Bilovus', '24 Riverside Circle', 'Apt 221', 'Aquamarine', '2709', 'irenardq@imdb.com', 'Isidor Renard', 'irenardq', '{noop}1234', 'GOOGLE', 'SELLER', null, null, null);
insert into user_account (deleted_yn, created_at, modified_at, created_by, modified_by, address1, address2, address_etc, zip_code, email, name, nickname,  password, oauth2_provider_info, roles, phone_number, business_name, business_number) values ('N', '2022-08-15', '2023-01-04', 'Torre Chalcraft', 'Ruperta Willcock', '84 Ridgeway Road', '1st Floor', 'Maroon', '6013', 'lsinnottr@theglobeandmail.com', 'Linnell Sinnott', 'lsinnottr',  '{noop}1234', 'GOOGLE', 'SELLER', null, null, null);
insert into user_account (deleted_yn, created_at, modified_at, created_by, modified_by, address1, address2, address_etc, zip_code, email, name, nickname,  password, oauth2_provider_info, roles, phone_number, business_name, business_number) values ('N', '2023-04-03', '2022-12-31', 'Georges Hynam', 'Louie Madre', '7 Everett Avenue', '9th Floor', 'Green', '25202', 'bosborns@jimdo.com', 'Bobby Osborn', 'bosborns', '{noop}1234', 'GOOGLE', 'SELLER', null, null, null);
insert into user_account (deleted_yn, created_at, modified_at, created_by, modified_by, address1, address2, address_etc, zip_code, email, name, nickname,  password, oauth2_provider_info, roles, phone_number, business_name, business_number) values ('N', '2022-07-25', '2023-03-24', 'Nancey Ochterlony', 'Ester McConnel', '84489 Packers Terrace', 'PO Box 57844', 'Orange', '7', 'sgrzest@typepad.com', 'Son Grzes', 'sgrzest', '{noop}1234', 'GOOGLE', 'SELLER', null, null, null);



insert into profile_image(image_url, original_name, representative_image_yn, unique_name, user_account_id) values
                                                                                                               ('/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','N','ab1.jpeg',1),
                                                                                                               ('/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','N','ab2.jpeg',2),
                                                                                                               ('/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','N','ab3.jpeg',3),
                                                                                                               ('/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','N','ab4.jpeg',4),
                                                                                                               ('/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','N','ab5.jpeg',5),
                                                                                                               ('/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','N','ab6.jpeg',6),
                                                                                                               ('/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','N','ab7.jpeg',7),
                                                                                                               ('/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','N','ab8.jpeg',8),
                                                                                                               ('/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','N','ab9.jpeg',9),
                                                                                                               ('/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','N','ab10.jpeg',10);



INSERT INTO item (created_at,created_by,modified_at,modified_by,deleted_yn, item_name, price, item_amount, item_detail, item_status, original_price, discount_rate, total_rating,  user_account_id) VALUES
(now(),'페크페크', now(),'페크페크','N','item1', 10000, 10, 'item1 detail', 'SELL', 10000,2,1, 2),
(now(),'페크페크', now(),'페크페크','N','item2', 11000, 20, 'item2 detail', 'SELL', 20000,2,1.5, 2),
(now(),'페크페크', now(),'페크페크','N','item3', 12000, 30, 'item3 detail', 'SELL', 30000,2,2, 2),
(now(),'페크페크', now(),'페크페크','N','item4', 13000, 40, 'item4 detail', 'SELL', 40000,2,2.5, 2),
(now(),'페크페크', now(),'페크페크','N','item5', 14000, 50, 'item5 detail', 'SELL', 50000,2,3, 2),
(now(),'페크페크', now(),'페크페크','N','item6', 15000, 60, 'item6 detail', 'SOLD_OUT', 60000,2,3.5, 4),
(now(),'페크페크', now(),'페크페크','N','item7', 20000, 70, 'item7 detail', 'SOLD_OUT', 70000,2,4, 4),
(now(),'페크페크', now(),'페크페크','N','item8', 30000, 80, 'item8 detail', 'SOLD_OUT', 80000,2,5, 4),
(now(),'페크페크', now(),'페크페크','N','item9', 40000, 90, 'item9 detail', 'SOLD_OUT', 90000,2,4, 4),
(now(),'페크페크', now(),'페크페크','N','item10', 50000, 100, 'item10 detail', 'SOLD_OUT', 100000,2,5, 4);

INSERT INTO item_image (created_at,created_by,modified_at,modified_by,deleted_yn,unique_name, original_name, image_url, representative_image_yn, item_id) VALUES
                                                                                      (now(),'페크페크', now(),'페크페크','N', 'abcde-34124.jpeg', '34124.jpeg', '/images/item/34124.jpeg', 'Y', 1),
                                                                                      (now(),'페크페크', now(),'페크페크','N', 'abcde-1224.jpeg', '1224.jpeg', '/images/item/1224.jpeg', 'N', 1),
                                                                                      (now(),'페크페크', now(),'페크페크','N', 'abcde-스크린샷 2023-04-19 오후 3.12.41.png', '스크린샷 2023-04-19 오후 3.12.41.png', '/images/item/스크린샷 2023-04-19 오후 3.12.41.png', 'N', 1),
                                                                                      (now(),'페크페크', now(),'페크페크','N', 'abcde-53232.jpeg', '53232.jpeg', '/images/item/53232.jpeg', 'N', 1),
                                                                                      (now(),'페크페크', now(),'페크페크','N', 'abcde-1KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', '/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'Y',2),
                                                                                      (now(),'페크페크', now(),'페크페크','N', 'abcde-2KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', '/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'N',2),
                                                                                      (now(),'페크페크', now(),'페크페크','N', 'abcde-3KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', '/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'Y',3),
                                                                                      (now(),'페크페크', now(),'페크페크','N', 'abcde-4KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', '/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'N',3),
                                                                                      (now(),'페크페크', now(),'페크페크','N', 'abcde-5KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', '/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'Y',4),
                                                                                      (now(),'페크페크', now(),'페크페크','N', 'abcde-6KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', '/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'N',4),
                                                                                      (now(),'페크페크', now(),'페크페크','N', 'abcde-7KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', '/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'Y',5),
                                                                                      (now(),'페크페크', now(),'페크페크','N', 'abcde-8KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', '/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'Y',6),
                                                                                      (now(),'페크페크', now(),'페크페크','N', 'abcde-9KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', '/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'N',6),
                                                                                      (now(),'페크페크', now(),'페크페크','N', 'abcde-10KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', '/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'Y',7),
                                                                                      (now(),'페크페크', now(),'페크페크','N', 'abcde-11KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', '/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'N',7),
                                                                                      (now(),'페크페크', now(),'페크페크','N', 'abcde-12KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', '/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'N',7),
                                                                                      (now(),'페크페크', now(),'페크페크','N', 'abcde-13KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', '/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'Y',8),
                                                                                      (now(),'페크페크', now(),'페크페크','N', 'abcde-14KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', '/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'N',8),
                                                                                      (now(),'페크페크', now(),'페크페크','N', 'abcde-15KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', '/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'Y',9),
                                                                                      (now(),'페크페크', now(),'페크페크','N', 'abcde-16KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', '/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'N',9),
                                                                                      (now(),'페크페크', now(),'페크페크','N', 'abcde-17KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', '/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'Y',10),
                                                                                      (now(),'페크페크', now(),'페크페크','N', 'abcde-18KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', '/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'N',10);



insert into review(created_at, created_by, modified_at, modified_by, deleted_yn, content, likes, rating, item_id, user_account_id) values
(now(),'페크페크', now(),'페크페크','N','리뷰 테스트 내용1',10,5,1,1),
(now(),'페크페크', now(),'페크페크','N','리뷰 테스트 내용2',10,5,1,2),
(now(),'페크페크', now(),'페크페크','N','리뷰 테스트 내용3',10,5,1,3),
(now(),'페크페크', now(),'페크페크','N','리뷰 테스트 내용4',10,5,2,4),
(now(),'페크페크', now(),'페크페크','N','리뷰 테스트 내용5',10,5,2,5),
(now(),'페크페크', now(),'페크페크','N','리뷰 테스트 내용6',10,5,2,6),
(now(),'페크페크', now(),'페크페크','N','리뷰 테스트 내용7',10,5,3,7),
(now(),'페크페크', now(),'페크페크','N','리뷰 테스트 내용8',10,5,3,1),
(now(),'페크페크', now(),'페크페크','N','리뷰 테스트 내용9',10,5,4,2),
(now(),'페크페크', now(),'페크페크','N','리뷰 테스트 내용10',10,5,4,3),
(now(),'페크페크', now(),'페크페크','N','리뷰 테스트 내용11',10,5,5,4),
(now(),'페크페크', now(),'페크페크','N','리뷰 테스트 내용12',10,5,5,5),
(now(),'페크페크', now(),'페크페크','N','리뷰 테스트 내용13',10,5,6,6),
(now(),'페크페크', now(),'페크페크','N','리뷰 테스트 내용14',10,5,6,7),
(now(),'페크페크', now(),'페크페크','N','리뷰 테스트 내용15',10,5,7,8);

insert into review_image(created_at, created_by, modified_at, modified_by,deleted_yn,  image_url, original_name, unique_name, review_id) values
(now(),'페크페크', now(),'페크페크','N','/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','ab4.jpeg',3),
(now(),'페크페크', now(),'페크페크','N','/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','ab5.jpeg',4),
(now(),'페크페크', now(),'페크페크','N','/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','ab6.jpeg',5),
(now(),'페크페크', now(),'페크페크','N','/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','ab7.jpeg',5),
(now(),'페크페크', now(),'페크페크','N','/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','ab8.jpeg',6),
(now(),'페크페크', now(),'페크페크','N','/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','ab9.jpeg',6),
(now(),'페크페크', now(),'페크페크','N','/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','ab10.jpeg',6),
(now(),'페크페크', now(),'페크페크','N','/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','KakaoTalk_Photo_2023-01-05-22-29-06.jpeg','ab11.jpeg',7);

insert into review_likes(review_id, user_account_id) VALUES
(1,1),
(2,1),
(3,1),
(4,2),
(5,2),
(6,2),
(7,2),
(1,3);



















insert into article( created_at, created_by,  modified_at, modified_by, deleted_yn, category_type, title, content, hit, user_account_id) VALUES
(now(),'테스트 유저', now(),'테스트 유저1','N','DOG', '테스트용 타이틀입니다1', '테스트용 내용입니다1', 0, 1),
(now(),'테스트 유저', now(),'테스트 유저2','N','DOG', '테스트용 타이틀입니다2', '테스트용 내용입니다2', 1, 1),
(now(),'테스트 유저', now(),'테스트 유저3','N','CAT', '테스트용 타이틀입니다3', '테스트용 내용입니다3', 2, 1),
(now(),'테스트 유저', now(),'테스트 유저4','N','CAT', '테스트용 타이틀입니다4', '테스트용 내용입니다4', 3, 1),
(now(),'테스트 유저', now(),'테스트 유저5','N','CAT', '테스트용 타이틀입니다5', '테스트용 내용입니다5', 4, 2),
(now(),'테스트 유저', now(),'테스트 유저6','N','CAT', '테스트용 타이틀입니다6', '테스트용 내용입니다6', 5, 2),
(now(),'테스트 유저', now(),'테스트 유저7','N','CAT', '테스트용 타이틀입니다7', '테스트용 내용입니다7', 1, 2),
(now(),'테스트 유저', now(),'테스트 유저8','N','DOG', '테스트용 타이틀입니다8', '테스트용 내용입니다8', 2, 2),
(now(),'테스트 유저', now(),'테스트 유저9','N','DOG', '테스트용 타이틀입니다9', '테스트용 내용입니다9', 3, 2),
(now(),'테스트 유저', now(),'테스트 유저10','N','CAT', '테스트용 타이틀입니다10', '테스트용 내용입니다10', 4, 2),
(now(),'테스트 유저', now(),'테스트 유저11','N','DOG', '테스트용 타이틀입니다11', '테스트용 내용입니다11', 10, 2),
(now(),'테스트 유저', now(),'테스트 유저12','N','DOG', '테스트용 타이틀입니다12', '테스트용 내용입니다12', 10, 2),
(now(),'테스트 유저', now(),'테스트 유저13','N','DOG', '테스트용 타이틀입니다13', '테스트용 내용입니다13', 10, 2),
(now(),'테스트 유저', now(),'테스트 유저14','N','DOG', '테스트용 타이틀입니다14', '테스트용 내용입니다14', 10, 2),
(now(),'테스트 유저', now(),'테스트 유저15','N','DOG', '테스트용 타이틀입니다15', '테스트용 내용입니다15', 1, 2),
(now(),'테스트 유저', now(),'테스트 유저16','N','DOG', '테스트용 타이틀입니다16', '테스트용 내용입니다16', 2, 4),
(now(),'테스트 유저', now(),'테스트 유저17','N','CAT', '테스트용 타이틀입니다17', '테스트용 내용입니다17', 3, 4),
(now(),'테스트 유저', now(),'테스트 유저','N','CAT', '테스트용 타이틀입니다18', '테스트용 내용입니다18', 3, 4),
(now(),'테스트 유저', now(),'테스트 유저','N','CAT', '테스트용 타이틀입니다19', '테스트용 내용입니다19', 4, 4),
(now(),'테스트 유저', now(),'테스트 유저','N','CAT', '테스트용 타이틀입니다20', '테스트용 내용입니다20', 5, 4),
(now(),'테스트 유저', now(),'테스트 유저','N','CAT', '테스트용 타이틀입니다21', '테스트용 내용입니다21', 6, 4),
(now(),'테스트 유저', now(),'테스트 유저','N','CAT', '테스트용 타이틀입니다22', '테스트용 내용입니다22', 7, 4),
(now(),'테스트 유저', now(),'테스트 유저','N','CAT', '테스트용 타이틀입니다23', '테스트용 내용입니다23', 10, 5),
(now(),'테스트 유저', now(),'테스트 유저','N','DOG', '테스트용 타이틀입니다24', '테스트용 내용입니다24', 20, 5),
(now(),'테스트 유저', now(),'테스트 유저','N','DOG', '테스트용 타이틀입니다25', '테스트용 내용입니다25', 30, 5),
(now(),'테스트 유저', now(),'테스트 유저','N','DOG', '테스트용 타이틀입니다26', '테스트용 내용입니다26', 11, 5),
(now(),'테스트 유저', now(),'테스트 유저','N','DOG', '테스트용 타이틀입니다27', '테스트용 내용입니다27', 12, 5),
(now(),'테스트 유저', now(),'테스트 유저','N','DOG', '테스트용 타이틀입니다28', '테스트용 내용입니다28', 13, 5),
(now(),'테스트 유저', now(),'테스트 유저','N','DOG', '테스트용 타이틀입니다29', '테스트용 내용입니다29', 14, 5),
(now(),'테스트 유저', now(),'테스트 유저','N','DOG', '테스트용 타이틀입니다30', '테스트용 내용입니다30', 15, 6),
(now(),'테스트 유저', now(),'테스트 유저','N','DOG', '테스트용 타이틀입니다31', '테스트용 내용입니다31', 1, 6),
(now(),'테스트 유저', now(),'테스트 유저','N','DOG', '테스트용 타이틀입니다32', '테스트용 내용입니다32', 0, 6),
(now(),'테스트 유저', now(),'테스트 유저','N','DOG', '테스트용 타이틀입니다33', '테스트용 내용입니다33', 0, 6),
(now(),'테스트 유저', now(),'테스트 유저','N','DOG', '테스트용 타이틀입니다34', '테스트용 내용입니다34', 0, 6),
(now(),'테스트 유저', now(),'테스트 유저','N','DOG', '테스트용 타이틀입니다35', '테스트용 내용입니다35', 0, 7),
(now(),'테스트 유저', now(),'테스트 유저','N','LITTLE_ANIMALS', '테스트용 타이틀입니다36', '테스트용 내용입니다36', 0, 7),
(now(),'테스트 유저', now(),'테스트 유저','N','LITTLE_ANIMALS', '테스트용 타이틀입니다37', '테스트용 내용입니다37', 0, 7),
(now(),'테스트 유저', now(),'테스트 유저','N','LITTLE_ANIMALS', '테스트용 타이틀입니다38', '테스트용 내용입니다38', 0, 7),
(now(),'테스트 유저', now(),'테스트 유저','N','LITTLE_ANIMALS', '테스트용 타이틀입니다39', '테스트용 내용입니다39', 1, 7),
(now(),'테스트 유저', now(),'테스트 유저','N','LITTLE_ANIMALS', '테스트용 타이틀입니다40', '테스트용 내용입니다40', 1, 7),
(now(),'테스트 유저', now(),'테스트 유저','N','LITTLE_ANIMALS', '테스트용 타이틀입니다41', '테스트용 내용입니다41', 2, 7),
(now(),'테스트 유저', now(),'테스트 유저','N','LITTLE_ANIMALS', '테스트용 타이틀입니다42', '테스트용 내용입니다42', 2, 7),
(now(),'테스트 유저', now(),'테스트 유저','N','LITTLE_ANIMALS', '테스트용 타이틀입니다43', '테스트용 내용입니다43', 3, 8),
(now(),'테스트 유저', now(),'테스트 유저','N','LITTLE_ANIMALS', '테스트용 타이틀입니다44', '테스트용 내용입니다44', 3, 8),
(now(),'테스트 유저', now(),'테스트 유저','N','LITTLE_ANIMALS', '테스트용 타이틀입니다45', '테스트용 내용입니다45', 3, 8),
(now(),'테스트 유저', now(),'테스트 유저','N','OTHERS', '테스트용 타이틀입니다24', '테스트용 내용입니다46', 0, 9),
(now(),'테스트 유저', now(),'테스트 유저','N','OTHERS', '테스트용 타이틀입니다24', '테스트용 내용입니다47', 0, 9),
(now(),'테스트 유저', now(),'테스트 유저','N','OTHERS', '테스트용 타이틀입니다24', '테스트용 내용입니다48', 0, 10),
(now(),'테스트 유저', now(),'테스트 유저','N','OTHERS', '테스트용 타이틀입니다24', '테스트용 내용입니다49', 0, 10),
(now(),'테스트 유저', now(),'테스트 유저','N','OTHERS', '테스트용 타이틀입니다24', '테스트용 내용입니다50', 0, 10),
(now(),'테스트 유저', now(),'테스트 유저','N','OTHERS', '테스트용 타이틀입니다24', '테스트용 내용입니다51', 0, 10);

INSERT INTO article_image (created_at,created_by,modified_at,modified_by,deleted_yn,unique_name, original_name, image_url, representative_image_yn, article_id) VALUES
                                                                                                                                                              (now(),'페크페크', now(),'페크페크','N', 'abcde-34124.jpeg', '34124.jpeg', '/images/item/34124.jpeg', 'Y', 1),
                                                                                                                                                              (now(),'페크페크', now(),'페크페크','N', 'abcde-1224.jpeg', '1224.jpeg', '/images/item/1224.jpeg', 'N', 1),
                                                                                                                                                              (now(),'페크페크', now(),'페크페크','N', 'abcde-스크린샷 2023-04-19 오후 3.12.41.png', '스크린샷 2023-04-19 오후 3.12.41.png', '/images/item/스크린샷 2023-04-19 오후 3.12.41.png', 'N', 1),
                                                                                                                                                              (now(),'페크페크', now(),'페크페크','N', 'abcde-53232.jpeg', '53232.jpeg', '/images/item/53232.jpeg', 'Y', 2),
                                                                                                                                                              (now(),'페크페크', now(),'페크페크','N', 'abcde2-53232.jpeg', '53232.jpeg', '/images/item/53232.jpeg', 'N', 2),
                                                                                                                                                              (now(),'페크페크', now(),'페크페크','N', 'abcde3-53232.jpeg', '53232.jpeg', '/images/item/53232.jpeg', 'N', 2),
                                                                                                                                                              (now(),'페크페크', now(),'페크페크','N', 'abcde4-53232.jpeg', '53232.jpeg', '/images/item/53232.jpeg', 'Y', 3),
                                                                                                                                                              (now(),'페크페크', now(),'페크페크','N', 'abcde5-53232.jpeg', '53232.jpeg', '/images/item/53232.jpeg', 'N', 3),
                                                                                                                                                              (now(),'페크페크', now(),'페크페크','N', 'abcde6-53232.jpeg', '53232.jpeg', '/images/item/53232.jpeg', 'N', 3),
                                                                                                                                                              (now(),'페크페크', now(),'페크페크','N', 'abcde7-53232.jpeg', '53232.jpeg', '/images/item/53232.jpeg', 'Y', 4),
                                                                                                                                                              (now(),'페크페크', now(),'페크페크','N', 'abcde8-53232.jpeg', '53232.jpeg', '/images/item/53232.jpeg', 'N', 4),
                                                                                                                                                              (now(),'페크페크', now(),'페크페크','N', 'abcde9-53232.jpeg', '53232.jpeg', '/images/item/53232.jpeg', 'N', 4),
                                                                                                                                                              (now(),'페크페크', now(),'페크페크','N', 'abcde10-53232.jpeg', '53232.jpeg', '/images/item/53232.jpeg', 'Y', 5),
                                                                                                                                                              (now(),'페크페크', now(),'페크페크','N', 'abcde11-53232.jpeg', '53232.jpeg', '/images/item/53232.jpeg', 'N', 5),
                                                                                                                                                              (now(),'페크페크', now(),'페크페크','N', 'abcde12-53232.jpeg', '53232.jpeg', '/images/item/53232.jpeg', 'N', 5),
                                                                                                                                                              (now(),'페크페크', now(),'페크페크','N', 'abcde13-53232.jpeg', '53232.jpeg', '/images/item/53232.jpeg', 'Y', 6),
                                                                                                                                                              (now(),'페크페크', now(),'페크페크','N', 'abcde14-53232.jpeg', '53232.jpeg', '/images/item/53232.jpeg', 'N', 6),
                                                                                                                                                              (now(),'페크페크', now(),'페크페크','N', 'abcde15-53232.jpeg', '53232.jpeg', '/images/item/53232.jpeg', 'Y', 7),
                                                                                                                                                              (now(),'페크페크', now(),'페크페크','N', 'abcde16-53232.jpeg', '53232.jpeg', '/images/item/53232.jpeg', 'N', 7),
                                                                                                                                                              (now(),'페크페크', now(),'페크페크','N', 'abcde17-53232.jpeg', '53232.jpeg', '/images/item/53232.jpeg', 'N', 7),
                                                                                                                                                              (now(),'페크페크', now(),'페크페크','N', 'abcde18-53232.jpeg', '53232.jpeg', '/images/item/53232.jpeg', 'Y', 8),
                                                                                                                                                              (now(),'페크페크', now(),'페크페크','N', 'abcde19-53232.jpeg', '53232.jpeg', '/images/item/53232.jpeg', 'N', 8),
                                                                                                                                                              (now(),'페크페크', now(),'페크페크','N', 'abcde20-53232.jpeg', '53232.jpeg', '/images/item/53232.jpeg', 'Y', 9),
                                                                                                                                                              (now(),'페크페크', now(),'페크페크','N', 'abcde21-53232.jpeg', '53232.jpeg', '/images/item/53232.jpeg', 'N', 9),
                                                                                                                                                              (now(),'페크페크', now(),'페크페크','N', 'abcde22-53232.jpeg', '53232.jpeg', '/images/item/53232.jpeg', 'Y', 10),
                                                                                                                                                              (now(),'페크페크', now(),'페크페크','N', 'abcde23-53232.jpeg', '53232.jpeg', '/images/item/53232.jpeg', 'Y', 11),
                                                                                                                                                              (now(),'페크페크', now(),'페크페크','N', 'abcde24-53232.jpeg', '53232.jpeg', '/images/item/53232.jpeg', 'Y', 12),
                                                                                                                                                              (now(),'페크페크', now(),'페크페크','N', 'abcde25-53232.jpeg', '53232.jpeg', '/images/item/53232.jpeg', 'Y', 13),
                                                                                                                                                              (now(),'페크페크', now(),'페크페크','N', 'abcde26-53232.jpeg', '53232.jpeg', '/images/item/53232.jpeg', 'Y', 14),
                                                                                                                                                              (now(),'페크페크', now(),'페크페크','N', 'abcde27-53232.jpeg', '53232.jpeg', '/images/item/53232.jpeg', 'Y', 15),
                                                                                                                                                              (now(),'페크페크', now(),'페크페크','N', 'abcde28-53232.jpeg', '53232.jpeg', '/images/item/53232.jpeg', 'Y', 16),
                                                                                                                                                              (now(),'페크페크', now(),'페크페크','N', 'abcde-KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', '/images/item/KakaoTalk_Photo_2023-01-05-22-29-06.jpeg', 'N',2);

insert into hashtag(created_at,created_by,modified_at,modified_by,deleted_yn, hashtag_name, hit) VALUES
(now(),'테스트 유저', now(),'테스트 유저','N', 'hashtag1', 0),
(now(),'테스트 유저', now(),'테스트 유저','N', 'hashtag2',1),
(now(),'테스트 유저', now(),'테스트 유저','N', 'hashtag3',2),
(now(),'테스트 유저', now(),'테스트 유저','N', 'hashtag4',3),
(now(),'테스트 유저', now(),'테스트 유저','N', 'hashtag5',10),
(now(),'테스트 유저', now(),'테스트 유저','N', 'hashtag6',2),
(now(),'테스트 유저', now(),'테스트 유저','N', 'hashtag7',1),
(now(),'테스트 유저', now(),'테스트 유저','N', 'hashtag8',11),
(now(),'테스트 유저', now(),'테스트 유저','N', 'hashtag9',15),
(now(),'테스트 유저', now(),'테스트 유저','N', '해시태그',289),
(now(),'테스트 유저', now(),'테스트 유저','N', '강아지',0),
(now(),'테스트 유저', now(),'테스트 유저','N', '고양이',2),
(now(),'테스트 유저', now(),'테스트 유저','N', '곰',3),
(now(),'테스트 유저', now(),'테스트 유저','N', '아기곰',4),
(now(),'테스트 유저', now(),'테스트 유저','N', '상어',10),
(now(),'테스트 유저', now(),'테스트 유저','N', '고래',19),
(now(),'테스트 유저', now(),'테스트 유저','N', '물고기',30),
(now(),'테스트 유저', now(),'테스트 유저','N', '다람쥐',40),
(now(),'테스트 유저', now(),'테스트 유저','N', '토끼',100),
(now(),'테스트 유저', now(),'테스트 유저','N', '도마뱀',12),
(now(),'테스트 유저', now(),'테스트 유저','N', '갱얼쥐',15),
(now(),'테스트 유저', now(),'테스트 유저','N', '강아쥐',16),
(now(),'테스트 유저', now(),'테스트 유저','N', '뱀',19),
(now(),'테스트 유저', now(),'테스트 유저','N', '아기뱀',20),
(now(),'테스트 유저', now(),'테스트 유저','N', '독수리',30),
(now(),'테스트 유저', now(),'테스트 유저','N', '참새',40),
(now(),'테스트 유저', now(),'테스트 유저','N', '새',50),
(now(),'테스트 유저', now(),'테스트 유저','N', '하이에나',10),
(now(),'테스트 유저', now(),'테스트 유저','N', '사자',20),
(now(),'테스트 유저', now(),'테스트 유저','N', '하마',30),
(now(),'테스트 유저', now(),'테스트 유저','N', '기린',30),
(now(),'테스트 유저', now(),'테스트 유저','N', '코뿔소',30),
(now(),'테스트 유저', now(),'테스트 유저','N', '공룡',10),
(now(),'테스트 유저', now(),'테스트 유저','N', '장바구니',10),
(now(),'테스트 유저', now(),'테스트 유저','N', '호랑이',10),
(now(),'테스트 유저', now(),'테스트 유저','N', '아기호랑이',10),
(now(),'테스트 유저', now(),'테스트 유저','N', '바람',20),
(now(),'테스트 유저', now(),'테스트 유저','N', '인스타',30),
(now(),'테스트 유저', now(),'테스트 유저','N', '고냥이',40),
(now(),'테스트 유저', now(),'테스트 유저','N', '동물',10),
(now(),'테스트 유저', now(),'테스트 유저','N', '동물사랑',10);


insert into article_hashtag(article_id, hashtag_id) VALUES
(1,1),
(2,2),
(3,3),
(4,4),
(5,5),
(1,2),
(1,3),
(2,4),
(2,5),
(5,1),
(6,6),
(7,7),
(8,10),
(9,11),
(10,12),
(11,13),
(12,14),
(13,15),
(14,16),
(15,17),
(16,18),
(17,19),
(18,20),
(19,21),
(20,22),
(21,23),
(22,24),
(23,25),
(24,26),
(25,27),
(26,28),
(27,29),
(28,30),
(29,31),
(30,32),
(31,33),
(32,34),
(33,35),
(1,35),
(2,35),
(3,35),
(4,35),
(5,35),
(6,35),
(7,35),
(8,35),
(1,12),
(2,12),
(3,12),
(4,12),
(5,12),
(6,12),
(7,13),
(8,13),
(51,14),
(50,14),
(49,15),
(48,16),
(47,17),
(46,18),
(45,19),
(44,20),
(43,26),
(42,25),
(41,24),
(40,23),
(39,22),
(38,21),
(37,20),
(36,19),
(35,18),
(34,17),
(33,16),
(32,15),
(31,14),
(30,13);


insert into orders(created_at, created_by, deleted_yn, modified_at, modified_by, order_count, order_status, sale_percent, sale_price) VALUES
(NOW(),'테스트 유저1','N', now(),'테스트 유저1',4,'ORDER',0.0,0),
(NOW(),'테스트 유저2','N', now(),'테스트 유저2',1,'ORDER',0.0,0),
(NOW(),'테스트 유저3','N', now(),'테스트 유저3',1,'ORDER',0.0,0),
(NOW(),'테스트 유저4','N', now(),'테스트 유저4',1,'ORDER',0.0,0),
(NOW(),'테스트 유저5','N', now(),'테스트 유저5',1,'ORDER',0.0,0),
(NOW(),'테스트 유저6','N', now(),'테스트 유저6',1,'ORDER',0.0,0),
(NOW(),'테스트 유저7','N', now(),'테스트 유저7',1,'ORDER',0.0,0),
(NOW(),'테스트 유저8','N', now(),'테스트 유저8',1,'ORDER',0.0,0),
(NOW(),'테스트 유저9','N', now(),'테스트 유저9',1,'ORDER',0.0,0),
(NOW(),'테스트 유저10','N', now(),'테스트 유저10',1,'ORDER',0.0,0),
(NOW(),'테스트 유저11','N', now(),'테스트 유저11',1,'ORDER',0.0,0),
(NOW(),'테스트 유저12','N', now(),'테스트 유저12',1,'ORDER',0.0,0)   ;


insert into order_item( created_at, created_by, deleted_yn, modified_at, modified_by, order_count, order_price, order_status) VALUES
(NOW(),'테스트 유저1','N', now(),'테스트 유저1',4,10000,'ORDER'),
(NOW(),'테스트 유저2','N', now(),'테스트 유저2',3,10000,'ORDER'),
(NOW(),'테스트 유저3','N', now(),'테스트 유저3',4,10000,'ORDER'),
(NOW(),'테스트 유저4','N', now(),'테스트 유저4',1,10000,'ORDER'),
(NOW(),'테스트 유저5','N', now(),'테스트 유저5',2,10000,'ORDER'),
(NOW(),'테스트 유저6','N', now(),'테스트 유저6',5,10000,'ORDER'),
(NOW(),'테스트 유저7','N', now(),'테스트 유저7',6,10000,'ORDER'),
(NOW(),'테스트 유저8','N', now(),'테스트 유저8',2,10000,'ORDER'),
(NOW(),'테스트 유저9','N', now(),'테스트 유저9',2,10000,'ORDER'),
(NOW(),'테스트 유저10','N', now(),'테스트 유저10',1,10000,'ORDER'),
(NOW(),'테스트 유저11','N', now(),'테스트 유저11',1,10000,'ORDER'),
(NOW(),'테스트 유저12','N', now(),'테스트 유저12',4,10000,'ORDER');




insert into user_ask(created_at, created_by, deleted_yn, modified_at, modified_by, content, qna_category, phone_number, agree, answer_status,  user_account_id)
values
    (NOW(),'테스트 유저1','N', now(),'테스트 유저12', '궁금합니다. 질문합니다 1', '이용문의', '010-1234-1234', '동의', 'ANSWERED', 1),
    (NOW(),'테스트 유저1','N', now(),'테스트 유저12', '궁금합니다. 질문합니다 2', '이용문의', '010-1234-1234', '동의', 'ANSWERING', 1),
    (NOW(),'테스트 유저2','N', now(),'테스트 유저12', '궁금합니다. 질문합니다 3', '이용문의', '010-1234-1234', '동의', 'ANSWERED', 2),
    (NOW(),'테스트 유저2','N', now(),'테스트 유저12', '궁금합니다. 질문합니다 4', '결제/영수증', '010-1234-1234', '동의', 'ANSWERED', 2),
    (NOW(),'테스트 유저2','N', now(),'테스트 유저12', '궁금합니다. 질문합니다 5', '결제/영수', '010-1234-1234', '동의', 'ANSWERED', 2),
    (NOW(),'테스트 유저2','N', now(),'테스트 유저12', '궁금합니다. 질문합니다 6', '이용문의', '010-1234-1234', '동의', 'ANSWERING', 3),
    (NOW(),'테스트 유저3','N', now(),'테스트 유저12', '궁금합니다. 질문합니다 7', '배송 관련', '010-1234-1234', '동의', 'ANSWERED', 4),
    (NOW(),'테스트 유저4','N', now(),'테스트 유저12', '궁금합니다. 질문합니다 8', '배송 관련', '010-1234-1234', '동의', 'ANSWERED', 5),
    (NOW(),'테스트 유저5','N', now(),'테스트 유저12', '궁금합니다. 질문합니다 9 ', '취소/환불', '010-1234-1234', '동의', 'ANSWERED', 6),
    (NOW(),'테스트 유저6','N', now(),'테스트 유저12', '궁금합니다. 질문합니다 10', '반품/교환', '010-1234-1234', '동의', 'ANSWERING', 7),
    (NOW(),'테스트 유저7','N', now(),'테스트 유저12', '궁금합니다. 질문합니다 11', '반품/교환', '010-1234-1234', '동의', 'ANSWERING', 8),
    (NOW(),'테스트 유저12','N', now(),'테스트 유저12', '궁금합니다. 질문합니다 12', '로그인/회원정보', '010-1234-1234', '동의', 'ANSWERED', 9),
    (NOW(),'테스트 유저12','N', now(),'테스트 유저12', '궁금합니다. 질문합니다 13', '로그인/회원정보', '010-1234-1234', '동의', 'ANSWERING', 10),
    (NOW(),'테스트 유저12','N', now(),'테스트 유저12', '궁금합니다. 질문합니다 14', '서비스/기타', '010-1234-1234', '동의', 'ANSWERING', 11),
    (NOW(),'테스트 유저12','N', now(),'테스트 유저12', '궁금합니다. 질문합니다 15', '서비스/기타', '010-1234-1234', '동의', 'ANSWERED', 12);