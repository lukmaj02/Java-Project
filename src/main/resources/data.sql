--DODANIE UZYTKOWNIKOW
INSERT INTO uzytkownik (
            id_uzytkownika,
            imie,
            nazwisko,
            email,
            haslo,
            data_urodzenia,
            nr_telefonu)
    VALUES
        (random_uuid(),
        'Krzysztof',
        'Gonciarz',
        'krzysztof.gonciarz@gmail.com',
        'password123',
        '2002-12-10',
        '+48 123 456 789'),
        (random_uuid(),
        'Janusz',
        'Korwin',
        'janusz.korwin@gmail.com',
        'newpassword123',
        '1980-10-06',
        '+48 987 654 321'
        ),
        (random_uuid(),
        'Grazyna',
        'Wielka',
        'grazyna.wielka@gmail.com',
        'oldpassword123',
        '1950-01-01',
        '+48 192 837 465');
INSERT INTO konto_bankowe (id_uzytkownika,
                           id_konta,
                           numer_konta,
                           typ_konta,
                           saldo)
    VALUES
        (Select id_uzytkownika from UZYTKOWNIK where imie = 'Krzysztof' and nazwisko = 'Gonciarz',
        random_uuid(),
        '1234498274237423572352',
        'OSOBISTE',
        12345),
        (Select id_uzytkownika from UZYTKOWNIK where imie = 'Janusz' and nazwisko = 'Korwin',
        random_uuid(),
        '8424324235325325',
        'FIRMOWE',
        30000),
        (Select id_uzytkownika from UZYTKOWNIK where imie = 'Grazyna' and nazwisko = 'Wielka',
        random_uuid(),
        '49242350134234',
        'OSZCZEDNOSCIOWE',
        10000),
        (Select id_uzytkownika from UZYTKOWNIK where imie = 'Grazyna' and nazwisko = 'Wielka',
        random_uuid(),
        '49242350134234',
        'FIRMOWE',
        29000);

INSERT INTO transakcja (id_transakcji,
                        typ_transakcji,
                        status,
                        data)
VALUES
    (random_uuid(),
    'PRZELEW_TRADYCYJNY',
    TRUE,
    '2023-11-11'),
    (random_uuid(),
    'PRZELEW_EKSPRESOWY',
    FALSE,
    '2023-10-15');

INSERT INTO konto_transakcja (id_konta,
                              id_transakcji)
VALUES
    (SELECT id_konta FROM konto_bankowe
     where id_uzytkownika =  (SELECT id_uzytkownika FROM uzytkownik WHERE imie = 'Krzysztof'),
     SELECT id_transakcji FROM transakcja WHERE typ_transakcji = 'PRZELEW_EKSPRESOWY'),
     (SELECT id_konta FROM konto_bankowe
     where id_uzytkownika = (SELECT id_uzytkownika FROM uzytkownik WHERE imie = 'Janusz'),
     SELECT id_transakcji from transakcja WHERE typ_transakcji = 'PRZELEW_TRADYCYJNY');


INSERT INTO PRZELEW(id_przelewu,
                    data_przelewu,
                    kwota,
                    waluta,
                    id_transakcji)
VALUES
    (random_uuid(),
    '2023-10-10',
    '100.00',
    'ZLOTY',
    (SELECT id_transakcji FROM transakcja WHERE typ_transakcji = 'PRZELEW_TRADYCYJNY')),
    (random_uuid(),
    '2023-09-09',
    '150.00',
    'EURO',
    (SELECT id_transakcji FROM transakcja WHERE typ_transakcji = 'PRZELEW_EKSPRESOWY'));






