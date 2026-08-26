a) Czym sie rozni JPQL od zwyklego SQL? Na czym operujesz w JPQL?

    JPQL to jezyk zapytan ktory zamiast operowania na kolumnach w tabelach (jak to robi SQL) operuje na encjach w Javie,
    
b) Kiedy uzywasz @Query zamiast derived query? Podaj przyklad z tego cwiczenia.

    @Query uzywam gdy zapytanie jest albo malo czytelne w Derived Query albo wrecz niemozliwe.
    Do takich zapytan zaliczmy Group by czy order by oraz join
    Przykład: filtrowanie po typie i po zakresie kwoty
c) Co robi @Param i kiedy mozna go pominac?

        @Param laczy parametr metody Java z parametrem w zapytaniu JPQL(:nazwaParametru)
        Mozemy go pominac kiedy parametr ma te sama nazwe co argument metody ale dla pewnosci warto zawsze uzywac @Param.
d) Wklej wynik GET /policies/average/OC — jaki typ zwraca ten endpoint?

    1200.0
    Typ zwracany do Double
e) Wklej wynik GET /agents/top — czy kolejnosc agentow jest poprawna?

        [
    {
    "id": 1,
    "fullName": "Anna Kowalska",
    "email": "anna.kowalska@ubezpieczenia.pl",
    "policyNumbers": [
    "POL-2026-001",
    "POL-2026-002"
    ]
    },
    {
    "id": 2,
    "fullName": "Mariusz Pudzianowski",
    "email": "mariusz.pudzianowski@ubezpieczenia.pl",
    "policyNumbers": [
    "POL-2026-003"
    ]
    }
    ]
    Kolejnosc agentow jest poprawna najwyzej jest agent z najwieksza iloscia polis
f) Czym sie rozni @Query z JPQL od @Query z nativeQuery = true?

    @Query z JPQL jest uniwersalne przez co mozemy przenosic je do kazdej bazy danych
    @Query z nativeQuety = true uzywa skladni konkretnej bazy (wieksze mozliwosci ale przywiazanie do doatwacy)