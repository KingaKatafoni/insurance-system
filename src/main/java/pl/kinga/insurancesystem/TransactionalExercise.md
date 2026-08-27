a) Co robi @Transactional? Po co jest potrzebny?

        @Transactional to adnotacja w Spring ktrora robi doklanie to co Transakcje w bazach danych.
        Zastepuje pisanie reczne BEGIN COMMIT i ROLLBACK.
        Jest to potzrebne podczas wykonywania zmian na encjach w bazie danych 
        zwlaszcza gdy sa wykonywane 2 i wiecej operacji, aby miec pewnosc ze gdy cos pojdzie nie tak w trakcie modyfikacji 
        to gdy jedna operacja sie wykona to druga zeby nie zostala w eterze i aby obydwie wrocily do stanu z przed zmian.
b) Dlaczego stawiamy @Transactional na serwisie, a nie na kontrolerze lub repozytorium?

        @Transactional stawiamy w serwisie poniewaz jest to idelane miejsce, controller nie powinien wiedziec o tranzakcjach z reposytorium jest zbyt ogolne i kazda metoda w repo ma swoja mini transakcje.
c) Co to readOnly = true? Ktore metody oznaczylas tym parametrem?

        readOnly = true to flaga w adnotacji @Transactional. Sluzy ona do zaznaczenia ze transakcja bedzie sie odbywala bez zmian na encji, co powoduje lepsza optymalizacje.
        Metody ktore oznaczylam readOnly to: wszytskie metody z get i search w nazwie
d) Przetestuj transfer polisy do agenta 999 (nie istnieje). Czy polisa zostala u agenta-zrodla? Wklej wynik GET /agents/{fromId}

        {
    "error": "Not found Policy with id 999"
    } -> to wynik transferu kod 404 -> not found

    tutaj wynik get na agencie z polisa
    {
    "id": 1,
    "fullName": "Anna Kowalska",
    "email": "anna.kowalska@ubezpieczenia.pl",
    "policyNumbers": [
    "POL-2026-001"
    ]
    }
e) Usun @Transactional z transferPolicy i powtorz test. Co sie stalo? Czy wynik jest inny?

        wynik jest ten sam -> przy transferze blad a po transakcji polisa zostaje u agenta 1
        wiem ze to przez lazy loading i relacje @ManyToMany
f) Dla jakich wyjatkow @Transactional domyslnie robi rollback? Co z checked exceptions?
    
    @Transactional domyslnie robi rollback dla unchecked exceptions. Dla checked musimy sami dopisac adnotacje rollbackFor.