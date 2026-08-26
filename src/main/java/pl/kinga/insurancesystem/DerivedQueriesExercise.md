a) Czym sa derived queries? Jak Spring wie jaki SQL wygenerowac?

        Derivered queries to mechanizm automatycznego mapowania nazwy metody na zapytanie SQL.
        Dzieki konwencji nazewniczej Spring wie jak generowac SQL, 
        np findAgentByType(PolicyType type) -> SELECT * WHERE type = ?
b) Co sie stanie jesli w nazwie metody uzywasz pola ktore nie istnieje w encji?

        Jezeli w nazwie metody uzywam pola ktore nie istnieje w encji to aplikacja nie uruchomi sie i otrzymam blad.
c) Kiedy uzywasz Optional jako typ zwracany, a kiedy List?

    Optional uzywam kiedy mam wynik 0 lub 1 czyli np. szukam Agenta o id i albo go znajde i go zwracam 
    albo otrzymam null ze go nie ma i wtedy zabezpieczam sie orElse i odpowiednim kodem (not found)
d) Czym sie rozni @RequestParam od @PathVariable? Kiedy ktory?

        @RequestParam wyciaga parametr z query stringa po ?
        @PathVariable wyciaga wartosc z fragmentu sciezki
e) Wklej wynik GET /policies/expensive?min=1000

    [
    {
    "id": 1,
    "policyNumber": "POL-2026-001",
    "type": "OC",
    "holderName": "Jan Nowak",
    "premiumAmount": 1200.00,
    "startDate": "2026-08-25",
    "endDate": "2027-08-25"
    },
    {
    "id": 3,
    "policyNumber": "POL-2026-003",
    "type": "NA_ZYCIE",
    "holderName": "Kinga Binga",
    "premiumAmount": 12200.00,
    "startDate": "2026-08-26",
    "endDate": "2027-10-21"
    }
    ]
f) Wklej wynik GET /policies/count/OC — jaki typ zwraca ten endpoint?
    
    1 
    Zwraca long