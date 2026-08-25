a) Czym sie rozni @OneToMany od @ManyToMany? (swoimi slowami)

    Relacja @OneToMany jest wtedy gdy jeden obiekt jednej encji moze miec wiele obietow drugiej encji
    ale obiekt tej drugiej encji nalezy do dokladnie jednego.
    Relacja @ManyToMany jest wtedy gdy wiele encji moze miec wiele po drugiej stronie, polacznia miedzy nimi wymagaja tabeli posredniej.

b) Co to jest tabela posrednia i po co istnieje?
    
    Tabela posrednia jest tworzona automatycznie podczas worzenia realcji @ManyToMany.
    Jej zadaniem jest laczenie elementow jednej encji z druga.
c) Co robi mappedBy? Na ktorej stronie relacji je umieszczamy?

    mappedBy sluzy nam do poinformowania hej relacja zostala zdefiniowana i pole o nazwie podanej w mappedBy zawiera ramy tej relacji.
d) Wklej wynik GET /agents/1 po przypisaniu polisy — czy widzisz policyNumbers?

    {
    "id": 1,
    "fullName": "Anna Kowalska",
    "email": "anna.kowalska@ubezpieczenia.pl",
    "policyNumbers": [
    "POL-2026-001"
    ]
    }
    kod 200 -> created
e) Jaka tabela posrednia powstala w H2 Console? Jakie ma kolumny?
    
    Tabela posrednia o nazwie AGENT_POLICY
    Posiada dwie kolumny AGENT_ID i POLICY_ID
f) Co sie stanie jesli sprobujemy przypisac ta sama polise do agenta dwa razy?
    
    Ta sama polisa dodala sie dwa razy do jednego agenta 
     "policyNumbers": [
    "POL-2026-001",
    "POL-2026-001"
    ]   