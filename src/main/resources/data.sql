--DODANIE UZYTKOWNIKOW
INSERT INTO uzytkownik (
            id_uzytkownika,
            imie,
            nazwisko,
            email,
            haslo,
            data_urodzenia,
            adres,
            nr_telefonu)
    VALUES
        (random_uuid(),
        'Krzysztof',
        'Gonciarz',
        'krzysztof.gonciarz@gmail.com',
        'password123',
        '2002-12-10',
        'Kwiatowa 4a, Krakow',
        '123456789'),
        (random_uuid(),
        'Janusz',
        'Korwin',
        'janusz.korwin@gmail.com',
        'newpassword123',
        '1980-10-06',
        'Polna 64, Krakow',
        '987654321'
        ),
        (random_uuid(),
        'Grazyna',
        'Wielka',
        'grazyna.wielka@gmail.com',
        'oldpassword123',
        '1950-01-01',
        'Sloneczna 5, Krakow',
        '192837465');
--DODANIE KONT BANKOWYCH
INSERT INTO konto_bankowe (id_uzytkownika,
                           id_konta,
                           nr_konta,
                           typ_konta,
                           saldo,
                           waluta)
    VALUES
        ((Select id_uzytkownika from UZYTKOWNIK where imie = 'Krzysztof' and nazwisko = 'Gonciarz'),
        random_uuid(),
        '78190456231890724568903214',
        'OSOBISTE',
        12345,
        'ZLOTY'),
        ((Select id_uzytkownika from UZYTKOWNIK where imie = 'Janusz' and nazwisko = 'Korwin'),
        random_uuid(),
        '59013467890234126789012345',
        'FIRMOWE',
        30000,
        'ZLOTY'),
        ((Select id_uzytkownika from UZYTKOWNIK where imie = 'Grazyna' and nazwisko = 'Wielka'),
        random_uuid(),
        '45678901234567890123456789',
        'OSZCZEDNOSCIOWE',
        10000,
        'ZLOTY'),
        ((Select id_uzytkownika from UZYTKOWNIK where imie = 'Grazyna' and nazwisko = 'Wielka'),
        random_uuid(),
        '98765432109876543210987654',
        'FIRMOWE',
        29000,
        'ZLOTY');

--DODANIE TRANSAKCJI
INSERT INTO TRANSAKCJA (id_transakcji,
                       typ_transakcji,
                       status,
                       data_transakcji,
                       kwota,
                       tytul,
                       waluta,
                       do_konta,
                       z_konta)
VALUES
    (random_uuid(),
    'PRZELEW_EKSPRESOWY',
    TRUE,
    '2023-10-10',
    1500.00,
    'Na zakupy',
    'ZLOTY',
    '78190456231890724568903214',
    '45678901234567890123456789');

INSERT INTO TRANSAKCJA (id_transakcji,
                       typ_transakcji,
                       status,
                       data_transakcji,
                       kwota,
                       tytul,
                       waluta,
                       do_konta,
                       z_konta)
VALUES
    (random_uuid(),
    'PRZELEW_EKSPRESOWY',
    FALSE,
    '2023-08-08',
    700.00,
    'Na buty',
    'ZLOTY',
    '78190456231890724568903214',
    '45678901234567890123456789'),
    (random_uuid(),
    'PRZELEW_EKSPRESOWY',
    TRUE,
    '2023-10-10',
    1500.00,
    'Na zakupy',
    'ZLOTY',
    '78190456231890724568903214',
    '45678901234567890123456789'),
    (random_uuid(),
    'PRZELEW_EKSPRESOWY',
    TRUE,
    '2023-10-10',
    1500.00,
    'Na zakupy',
    'ZLOTY',
    '78190456231890724568903214',
    '45678901234567890123456789'),
    (random_uuid(),
    'PRZELEW_EKSPRESOWY',
    TRUE,
    '2023-10-10',
    1500.00,
    'Na zakupy',
    'ZLOTY',
    '78190456231890724568903214',
    '45678901234567890123456789'),
    (random_uuid(),
    'PRZELEW_EKSPRESOWY',
    TRUE,
    '2023-10-10',
    1500.00,
    'Na zakupy',
    'ZLOTY',
    '78190456231890724568903214',
    '45678901234567890123456789'),
    (random_uuid(),
    'PRZELEW_EKSPRESOWY',
    TRUE,
    '2023-10-10',
    1500.00,
    'Na zakupy',
    'ZLOTY',
    '78190456231890724568903214',
    '45678901234567890123456789'),
    (random_uuid(),
    'PRZELEW_EKSPRESOWY',
    TRUE,
    '2023-10-10',
    1500.00,
    'Na zakupy',
    'ZLOTY',
    '78190456231890724568903214',
    '45678901234567890123456789'),
    (random_uuid(),
    'PRZELEW_EKSPRESOWY',
    TRUE,
    '2023-10-10',
    1500.00,
    'Na zakupy',
    'ZLOTY',
    '78190456231890724568903214',
    '45678901234567890123456789'),
    (random_uuid(),
    'PRZELEW_EKSPRESOWY',
    TRUE,
    '2023-10-10',
    1500.00,
    'Na zakupy',
    'ZLOTY',
    '78190456231890724568903214',
    '45678901234567890123456789'),
    (random_uuid(),
    'PRZELEW_EKSPRESOWY',
    TRUE,
    '2023-10-10',
    1500.00,
    'Na zakupy',
    'ZLOTY',
    '78190456231890724568903214',
    '45678901234567890123456789'),
    (random_uuid(),
    'PRZELEW_EKSPRESOWY',
    TRUE,
    '2023-10-10',
    1500.00,
    'Na zakupy',
    'ZLOTY',
    '78190456231890724568903214',
    '45678901234567890123456789');

--DODANIE KART PLATNICZYCH
INSERT INTO karta_platnicza (id_karty,
                            nr_karty,
                            data_waznosci,
                            cvc,
                            typ_karty,
                            id_konta,
                            czy_zablokowana)
VALUES
    (random_uuid(),
    '4859321765482093',
    '2028-10-10',
    '464',
    'KREDYTOWA',
    (SELECT id_konta FROM konto_bankowe WHERE nr_konta = '78190456231890724568903214'),
    FALSE),
    (random_uuid(),
    '7910654321890765',
    '2027-11-11',
    '555',
    'DEBETOWA',
    (SELECT id_konta FROM konto_bankowe WHERE nr_konta ='59013467890234126789012345'),
    FALSE);



-- Przykład 1
INSERT INTO TRANSAKCJA (id_transakcji, typ_transakcji, status, data_transakcji, kwota, tytul, waluta, do_konta, z_konta)
VALUES (random_uuid(), 'PRZELEW_EKSPRESOWY', FALSE, '2023-08-08', 700.00, 'Na buty', 'ZLOTY', '78190456231890724568903214', '45678901234567890123456789');

-- Przykład 2
INSERT INTO TRANSAKCJA (id_transakcji, typ_transakcji, status, data_transakcji, kwota, tytul, waluta, do_konta, z_konta)
VALUES (random_uuid(), 'PRZELEW_EKSPRESOWY', FALSE, '2023-08-09', 850.00, 'Płatność za usługi', 'ZLOTY', '78190456231890724568903214', '45678901234567890123456789');

-- Przykład 3
INSERT INTO TRANSAKCJA (id_transakcji, typ_transakcji, status, data_transakcji, kwota, tytul, waluta, do_konta, z_konta)
VALUES (random_uuid(), 'PRZELEW_EKSPRESOWY', FALSE, '2023-08-10', 500.00, 'Zakupy spożywcze', 'ZLOTY', '78190456231890724568903214', '45678901234567890123456789');

-- Przykład 4
INSERT INTO TRANSAKCJA (id_transakcji, typ_transakcji, status, data_transakcji, kwota, tytul, waluta, do_konta, z_konta)
VALUES (random_uuid(), 'PRZELEW_EKSPRESOWY', FALSE, '2023-08-11', 300.00, 'Kino', 'ZLOTY', '78190456231890724568903214', '45678901234567890123456789');

-- Przykład 5
INSERT INTO TRANSAKCJA (id_transakcji, typ_transakcji, status, data_transakcji, kwota, tytul, waluta, do_konta, z_konta)
VALUES (random_uuid(), 'PRZELEW_EKSPRESOWY', FALSE, '2023-08-12', 1200.00, 'Nowy laptop', 'ZLOTY', '78190456231890724568903214', '45678901234567890123456789');

-- Przykład 6
INSERT INTO TRANSAKCJA (id_transakcji, typ_transakcji, status, data_transakcji, kwota, tytul, waluta, do_konta, z_konta)
VALUES (random_uuid(), 'PRZELEW_EKSPRESOWY', FALSE, '2023-08-13', 450.00, 'Opłata za telefon', 'ZLOTY', '78190456231890724568903214', '45678901234567890123456789');

-- Przykład 7
INSERT INTO TRANSAKCJA (id_transakcji, typ_transakcji, status, data_transakcji, kwota, tytul, waluta, do_konta, z_konta)
VALUES (random_uuid(), 'PRZELEW_EKSPRESOWY', FALSE, '2023-08-14', 890.00, 'Bilet lotniczy', 'ZLOTY', '78190456231890724568903214', '45678901234567890123456789');

-- Przykład 8
INSERT INTO TRANSAKCJA (id_transakcji, typ_transakcji, status, data_transakcji, kwota, tytul, waluta, do_konta, z_konta)
VALUES (random_uuid(), 'PRZELEW_EKSPRESOWY', FALSE, '2023-08-15', 600.00, 'Przedszkole dla dziecka', 'ZLOTY', '78190456231890724568903214', '45678901234567890123456789');

-- Przykład 9
INSERT INTO TRANSAKCJA (id_transakcji, typ_transakcji, status, data_transakcji, kwota, tytul, waluta, do_konta, z_konta)
VALUES (random_uuid(), 'PRZELEW_EKSPRESOWY', FALSE, '2023-08-16', 320.00, 'Książki', 'ZLOTY', '78190456231890724568903214', '45678901234567890123456789');

-- Przykład 10
INSERT INTO TRANSAKCJA (id_transakcji, typ_transakcji, status, data_transakcji, kwota, tytul, waluta, do_konta, z_konta)
VALUES (random_uuid(), 'PRZELEW_EKSPRESOWY', FALSE, '2023-08-17', 800.00, 'Meble do salonu', 'ZLOTY', '78190456231890724568903214', '45678901234567890123456789');

-- Przykład 11
INSERT INTO TRANSAKCJA (id_transakcji, typ_transakcji, status, data_transakcji, kwota, tytul, waluta, do_konta, z_konta)
VALUES (random_uuid(), 'PRZELEW_EKSPRESOWY', FALSE, '2023-08-18', 950.00, 'Weekendowy wypad', 'ZLOTY', '78190456231890724568903214', '45678901234567890123456789');

-- Przykład 12
INSERT INTO TRANSAKCJA (id_transakcji, typ_transakcji, status, data_transakcji, kwota, tytul, waluta, do_konta, z_konta)
VALUES (random_uuid(), 'PRZELEW_EKSPRESOWY', FALSE, '2023-08-19', 200.00, 'Prezent urodzinowy', 'ZLOTY', '78190456231890724568903214', '45678901234567890123456789');

-- Przykład 13
INSERT INTO TRANSAKCJA (id_transakcji, typ_transakcji, status, data_transakcji, kwota, tytul, waluta, do_konta, z_konta)
VALUES (random_uuid(), 'PRZELEW_EKSPRESOWY', FALSE, '2023-08-20', 550.00, 'Sprzęt do campingu', 'ZLOTY', '78190456231890724568903214', '45678901234567890123456789');

-- Przykład 14
INSERT INTO TRANSAKCJA (id_transakcji, typ_transakcji, status, data_transakcji, kwota, tytul, waluta, do_konta, z_konta)
VALUES (random_uuid(), 'PRZELEW_EKSPRESOWY', FALSE, '2023-08-21', 740.00, 'Obiad w restauracji', 'ZLOTY', '78190456231890724568903214', '45678901234567890123456789');

-- Przykład 15
INSERT INTO TRANSAKCJA (id_transakcji, typ_transakcji, status, data_transakcji, kwota, tytul, waluta, do_konta, z_konta)
VALUES (random_uuid(), 'PRZELEW_EKSPRESOWY', FALSE, '2023-08-22', 380.00, 'Bilety do teatru', 'ZLOTY', '78190456231890724568903214', '45678901234567890123456789');

-- Przykład 16
INSERT INTO TRANSAKCJA (id_transakcji, typ_transakcji, status, data_transakcji, kwota, tytul, waluta, do_konta, z_konta)
VALUES (random_uuid(), 'PRZELEW_EKSPRESOWY', FALSE, '2023-08-23', 960.00, 'Wyposażenie kuchni', 'ZLOTY', '78190456231890724568903214', '45678901234567890123456789');

-- Przykład 17
INSERT INTO TRANSAKCJA (id_transakcji, typ_transakcji, status, data_transakcji, kwota, tytul, waluta, do_konta, z_konta)
VALUES (random_uuid(), 'PRZELEW_EKSPRESOWY', FALSE, '2023-08-24', 670.00, 'Abonament telewizyjny', 'ZLOTY', '78190456231890724568903214', '45678901234567890123456789');

-- Przykład 18
INSERT INTO TRANSAKCJA (id_transakcji, typ_transakcji, status, data_transakcji, kwota, tytul, waluta, do_konta, z_konta)
VALUES (random_uuid(), 'PRZELEW_EKSPRESOWY', FALSE, '2023-08-25', 520.00, 'Gadżety sportowe', 'ZLOTY', '78190456231890724568903214', '45678901234567890123456789');

-- Przykład 19
INSERT INTO TRANSAKCJA (id_transakcji, typ_transakcji, status, data_transakcji, kwota, tytul, waluta, do_konta, z_konta)
VALUES (random_uuid(), 'PRZELEW_EKSPRESOWY', FALSE, '2023-08-26', 1100.00, 'Zestaw do grillowania', 'ZLOTY', '78190456231890724568903214', '45678901234567890123456789');

-- Przykład 20
INSERT INTO TRANSAKCJA (id_transakcji, typ_transakcji, status, data_transakcji, kwota, tytul, waluta, do_konta, z_konta)
VALUES (random_uuid(), 'PRZELEW_EKSPRESOWY', FALSE, '2023-08-27', 290.00, 'Prezent ślubny', 'ZLOTY', '78190456231890724568903214', '45678901234567890123456789');

-- Przykład 21
INSERT INTO TRANSAKCJA (id_transakcji, typ_transakcji, status, data_transakcji, kwota, tytul, waluta, do_konta, z_konta)
VALUES (random_uuid(), 'PRZELEW_EKSPRESOWY', FALSE, '2023-08-28', 810.00, 'Fryzjer', 'ZLOTY', '78190456231890724568903214', '45678901234567890123456789');

-- Przykład 22
INSERT INTO TRANSAKCJA (id_transakcji, typ_transakcji, status, data_transakcji, kwota, tytul, waluta, do_konta, z_konta)
VALUES (random_uuid(), 'PRZELEW_EKSPRESOWY', FALSE, '2023-08-29', 660.00, 'Sprzątanie mieszkania', 'ZLOTY', '78190456231890724568903214', '45678901234567890123456789');

-- Przykład 23
INSERT INTO TRANSAKCJA (id_transakcji, typ_transakcji, status, data_transakcji, kwota, tytul, waluta, do_konta, z_konta)
VALUES (random_uuid(), 'PRZELEW_EKSPRESOWY', FALSE, '2023-08-30', 430.00, 'Bilety komunikacji miejskiej', 'ZLOTY', '78190456231890724568903214', '45678901234567890123456789');

-- Przykład 24
INSERT INTO TRANSAKCJA (id_transakcji, typ_transakcji, status, data_transakcji, kwota, tytul, waluta, do_konta, z_konta)
VALUES (random_uuid(), 'PRZELEW_EKSPRESOWY', FALSE, '2023-08-31', 740.00, 'Wyjście do kina', 'ZLOTY', '78190456231890724568903214', '45678901234567890123456789');

-- Przykład 25
INSERT INTO TRANSAKCJA (id_transakcji, typ_transakcji, status, data_transakcji, kwota, tytul, waluta, do_konta, z_konta)
VALUES (random_uuid(), 'PRZELEW_EKSPRESOWY', FALSE, '2023-09-01', 920.00, 'Zakup ubrań', 'ZLOTY', '78190456231890724568903214', '45678901234567890123456789');










