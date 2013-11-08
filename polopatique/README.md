



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
Install
=======

S Eclipsem neporadím, ale pro Ideu:

```
gradle idea
```

Vzniklý projekt otevřete v Idei a jste více/méně ready to go.
- Idea si sama očuchá projekt a přihlásí se s vhodnými frameworky (chtělo by je mít je ale nainstalované, např. hmm ... AppEngine)
- musíte ručně vytvořit vhodný artefakt, nejlépe exploded war z modulu "polopatique"
- a ještě si nakonfigurujte "Run Configuration" pro lokální spuštění AppEngine

Po nahození by mělo zafungovat:

```
curl --include --request GET http://localhost:8080/api/messages
```
Což by mělo vrátit prázdný seznam existujících stringů:

```
HTTP/1.1 200 OK
Content-Type: application/json
Server: Development/1.0
Date: Fri, 08 Nov 2013 19:00:49 GMT
Cache-Control: no-cache
Expires: Fri, 01 Jan 1990 00:00:00 GMT
Content-Length: 2

[]
```
