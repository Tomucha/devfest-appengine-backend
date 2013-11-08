devfest-appengine-backend
=========================

3 vzorové příklady jak udělat v AppEngine backend pro vaši mobilní app-ku.
Příklady jsou různě složité, podle toho, jak velké nároky na čistotu provedení máte.
S příbývající čistotou se množí vhodné technologie a tím se vše poněkud komplikuje.

U všech tří příkladů jde o totéž - jednoduchá AppEngine aplikace pro zprávu textů vaší aplikace.
Každý text má klíč a hodnotu. Jako to znáte např. z Androidu:

```
<string name="app.name">Tohle je moje app-ka</string>
```

Všechny příklady jsou vytvořeny tak, abyste je mohli snadno rozšiřovat o další funkce.

Polopatique
-----------

Nejjednodušším příkladem je [polopatique](polopatique). Ten implementuje pouze ty zcela úplně nejvíc
elementární požadavky:
- dependency injection
- REST API
- Objectify

To by mělo být celkem jasné, možná [Objectify](https://code.google.com/p/objectify-appengine/) není úplně rozšířené.
Použijte ho namísto JDO nebo JPA pro perzistenci vašich objektů. Je psané na míru AppEnginu a je to poznat.
