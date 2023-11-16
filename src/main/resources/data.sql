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










