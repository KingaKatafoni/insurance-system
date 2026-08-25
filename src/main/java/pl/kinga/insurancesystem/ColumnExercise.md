a) Jaka jest roznica miedzy @Column(nullable=false) a @NotNull? Kiedy stosujemy ktore?
    
    Roznica polega na tym ze za walidacje @Column odpowiada baza danych 
    a za walidacje @NotNull odpowiada Java (Bean Validation).
    Adnotacje Column umieszcamy w encji, a @NotNull na DTO
b) Dlaczego EnumType.STRING a nie ORDINAL? Co sie stanie jesli uzyjemy ORDINAL i dodamy nowy enum na poczatku?

    EnumType.String daje nam dostep do Stringowej reprezentacji enuma, a ORDINAL do numerow indeksow elementow w enumie.
    Gdy dodamy kolejny element na poczaatku, to przy Stringu nie ma to znaczenia ale na ORDINAL mamy 
    przesuniecie i moze dojsc do pomylki. Dlatego zawsze EnumType.String
c) Wklej wynik POST z typem "AC" — jaki kod?

    {
    "id": 1,
    "policyNumber": "POL-2026-002",
    "type": "AC",
    "holderName": "Jan Nowak",
    "premiumAmount": 2400.00,
    "startDate": "2026-08-21",
    "endDate": "2027-08-21"
    }
    kod 201 -> Created
d) Co sie stalo przy POST z typem "NIEZNANY_TYP"? Jaki kod i komunikat?
    
    {
    "error": "Nieprawidlowe dane w JSON"
    }
    kod 400 -> bad request
    wczesniej byl tylko komunikat bad request ale zmienilam z twoja pomoca GlobalHandler
e) Co sie stalo przy probie dodania duplikatu policyNumber? Jaki kod?
    
    {
    "error": "Naruszenie ograniczen bazy danych (np. duplikat)"
    }
    kod 409 -> Conflict, taka sama sytuacja byl brzydki error zmienilismy na czytelny komunikat
f) Wejdz na H2 Console — jaki typ ma kolumna type w tabeli? (VARCHAR? INT? inny?)

    kolumna type w tabeli ma typ ENUM