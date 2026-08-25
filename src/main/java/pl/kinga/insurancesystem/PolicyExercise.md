a) Czym rozni sie JPA od Hibernate? (swoimi slowami, 2-3 zdania)
        
    JPA to interfejs, spacyfikacja ktora definiuje jak mapowac obiekty Javy na tabele
    Hibernate to implementacja JPA
    JPA od Hiberante roznia sie jak List<> a ArrayList<>
    JPA porzebuje implementacji a Hibernate mozna uzyc samodzielnie.
    Hibernate implementue reguly ktore sa zdefiniowane w JPA
b) Co to jest ORM?

    Object_Relational Mapping -> technika mapowania obiektow Javy na tabele bazy danych
                                Hibernate to konkretny ORM
c) Wklej wynik POST /policies — jaki kod?


    {
    "id": 2,
    "policyNumber": "POL-2026-001",
    "type": "OC",
    "holderName": "Anna Kowalska",
    "premiumAmount": 1200.00,
    "startDate": "2026-08-21",
    "endDate": "2027-08-21"
    }
    Response file saved.
    > 2026-08-21T224415.201.json
    
    Response code: 201; -> kod OK
d) Wklej wynik GET /policies — czy widzisz wszystkie pola?
    
        [
    {
    "id": 1,
    "policyNumber": "POL-2026-001",
    "type": "OC",
    "holderName": "Anna Kowalska",
    "premiumAmount": 1200.00,
    "startDate": "2026-08-21",
    "endDate": "2027-08-21"
    }
    ]  
    tak widze wszytskie pola
e) Wklej wynik POST z pustym policyNumber — jaki kod i komunikat?

    {
    "policyNumber": "Numer polisy nie moze byc pusty" -> kominikat
    }
    Response file saved.
    > 2026-08-21T225841.400.json
    
    Response code: 400 kod -> bad request
f) Wymien wszystkie klasy ktore stworzyles. Ile ich jest?
        
    stworzylam 8 klas 
        Policy
        PolicyRepository
        PolicyService
        PolicyController
        PolicyRequest
        PolicyResponse
        DtoMapper
        GlobalExceptionHandler
    oraz test.http
g) Czy udalo Ci sie napisac kod z pamieci, czy musialas podgladac application-system? (szczera odpowiedz!)
        
    nie udalo mi sie napisac z pamieci kodu, moze do servisu pamietalam co i jak ale zastanawialam sie gdzie ma byc walidacja
    Kontroler byl masakra ale ladnie mi wyjasniles kiedy ResponseEntity a kiedy PolicyResponse
    Juz bylo lepiej bo bylam odpwoiedzialna za tworzenie od podstaw i obraz tego shcematu jest coraz bardziej czytelny.
    No dodatkowo meczylam sie z powodu braku komunikatu z walidacji i maz mi podpoweidzial ze moze GlobalExceptionHandler bylby potrezbny, totlanie zapomnialam go dodac.