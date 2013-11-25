devfest-appengine-backend
=========================

3 vzorové příklady jak udělat v AppEngine backend pro vaši mobilní app-ku.
Příklady jsou různě složité, podle toho, jak velké nároky na čistotu provedení máte.
S příbývající čistotou se množí vhodné technologie a tím se vše poněkud komplikuje.

U všech tří příkladů jde o totéž - jednoduchá AppEngine aplikace pro správu textů vaší aplikace.
Každý text má klíč a hodnotu. Jako to znáte např. z Androidu:

```
<string name="app.name">Tohle je moje app-ka</string>
```

Všechny příklady jsou vytvořeny tak, abyste je mohli snadno rozšiřovat o další funkce.  Vyhnul jsem se tomu, abych příklady
zasvinil nějakými svými postupy a návyky, mělo by to být pouze nezbytné minimum, které už si doohnete podle chuti.

Příklady (nebo "vzory", chcete-li) vznikly pro přednášku "AppEngine jako backend Vaší mobilní appky" na [DevFestCZ 2013](http://www.devfest.cz).

Polopatique
-----------

Nejjednodušším příkladem je [polopatique](polopatique) (čti: polopatyk). Ten implementuje pouze ty zcela úplně nejvíc
elementární požadavky:
- dependency injection
- REST API
- Objectify

To by mělo být celkem jasné, možná [Objectify](https://code.google.com/p/objectify-appengine/) není úplně rozšířené.
Použijte ho namísto JDO nebo JPA pro perzistenci vašich objektů. Je psané na míru AppEnginu a je to poznat.

Pokud použijete jako vzor pro svůj projekt "polopatique", bude te mít pěkně napsanou práci s daty, ale trochu ad-hoc
zbastlené API. Ale business logika bude cajk. A API samotné taky, jen ta jeho implementace bude taková .. na koleně,
pomocí obyčejného servletu. Na druhou stranu - nebudete se muset skoro nic nového učit.


Profique
--------

Tuhle kostřičku použiju pro příští projekt já: [profique](profique) (čti: profik). Services zůstávají stejné jako ve variantě [polopatique](polopatique),
ale přidáváme:
- REST API pomocí [Jersey](https://jersey.java.net/)
- AOP (pro příklad použité pouze na logování volání metod naservisách)
- request-scope objekt, do kterého si můžete ukládat např. identitu volajícího
- základ pro autorizaci pomocí HTTP hlavičky "Authorization"


Proprietique
------------
A ještě jsem se pokusil totéž vytvořit pomocí GAE endpoints, to najdete v adresáři [proprietique](proprietique) (čti: proprietik).
To mi bohužel funguje jen na lokálním SDK, po uploadu do GAE ne. No a protože se nechystám endpoints zatím používat,
moc se mi nechce se v tom plácat :-)

Zatím se pokochejte vzorovým [příkladem od Google](https://github.com/GoogleCloudPlatform/appengine-endpoints-tictactoe-java-maven).
Ten ale neřeší dependency injection, nepoužívá Objectify, ... tak to není pro mě.

A cože s tím?
=============

Pokud potřebujete minimalistický backend pro svoji mobilní aplikaci, stahujte [polopatique](polopatique). Nebudete
se muset učit nic moc nového, je to takové nezbytné minimum. Pokud chcete něco stabilnějšího, standardizovanějšího,
volte variantu [profique](profique).

Po stažení si přejmenovánejte, refaktorujte, ... podle libosti. A kdyžtak pište: zverina@m-atelier.cz - dotazy, bugy, cokoliv.
