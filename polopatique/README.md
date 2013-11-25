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

Gradle
------
V gradle používám plugin "gae", takže by vám alternativně mělo fungovat spuštění přes:

```
export APPENGINE_HOME=/moje/super/cesta/appengine-java-sdk-1.8.6/;
gradle gaeRun;
```
Kam dál
=======

Pokud zdárně rozběhnete, chcete se jít podívat na třídy:
- [Message](src/main/java/ma/demo/devfest/polopatique/domain/Message.java) - persitentní entita
- [MessageService](src/main/java/ma/demo/devfest/polopatique/service/MessageService.java) - servisa nad entitou
- [konfirace Guice](src/main/java/ma/demo/devfest/polopatique/guice/GuiceConfig.java)
- [implementace REST API](src/main/java/ma/demo/devfest/polopatique/web/api)
