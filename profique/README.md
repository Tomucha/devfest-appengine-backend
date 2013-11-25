Install
=======

Instaluje se to stejně jako tupější bratr [polopatique](../polopatique).

Kam dál
=======

Pokud zdárně rozběhnete, chcete se jít podívat na třídy:
- [Message](src/main/java/ma/demo/devfest/profique/domain/Message.java) - persitentní entita
- [MessageService](src/main/java/ma/demo/devfest/profique/service/MessageService.java) - servisa nad entitou
- [konfirace Guice](src/main/java/ma/demo/devfest/profique/guice/GuiceConfig.java)
- [REST API pomocí Jersey](src/main/java/ma/demo/devfest/profique/web/api/MessageResource.java)
- [AOP](src/main/java/ma/demo/devfest/profique/guice/ServiceModule.java)
- [konfigurace Jersey](src/main/java/ma/demo/devfest/profique/web/api/jersey)
- [autorizace pomocí hlavičky "Authorization"](/src/main/java/ma/demo/devfest/profique/web/api/jersey/JerseyAuthFilter.java) a [request-scope beany](src/main/java/ma/demo/devfest/profique/util/CallContext.java)