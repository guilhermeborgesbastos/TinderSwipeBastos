
# TinderSwipeBastos
Componente completo para quem precisa de um baralho de cartas dinamico e interativo para Android. Muito parecido com o que encontramos no Tinder. Código totalmente aberto para modificações e melhorias.

| Gif | Video |
| --- | --- |
| ![TinderSwipeBastos_animated](https://meucomercioeletronico.com/tutorial/TinderSwipeBastos_animated.gif)  | [![VIDEO](https://img.youtube.com/vi/r6qHrTARf2U/0.jpg)](https://www.youtube.com/watch?v=r6qHrTARf2U) |
  

## Instalação e uso
Basta importar o projeto do Git para o seu editor ( Android Studio / Eclipse, etc... )

## Configurações e informações úteis ##

#### Populando os cartões
No arquivo SquipeCardFragment ( Veja arquivo: https://goo.gl/sIBxC4 ), possuimos o seguinte código:

```
    //Populo o POJO com registros de teste ( Pode busca-los da sua API )
    recordSet = new ArrayList<>();
    
    Notification notification1 =  new Notification();
    notification1.setNome("Guilherme");
    notification1.setSobrenome("B. Bastos");
    notification1.setFoto("uploads/userprofile/A20CF1B8FFB203B95C40EB3BAFE4F78C.jpg");
    notification1.setNome_cidade("São Paulo");
    notification1.setNome_estado("SP");
    recordSet.add(notification1);
    
    Notification notification3 =  new Notification();
    notification3.setNome("Mark");
    notification3.setSobrenome("Zuckerberg");
    notification3.setFoto("uploads/userprofile/A20CF1B8FFA5f64as5saopds58asAFE4F78C.jpg");
    notification3.setNome_cidade("Palo Alto");
    notification3.setNome_estado("CA");
    recordSet.add(notification3);
    
    Notification notification2 =  new Notification();
    notification2.setNome("Bill");
    notification2.setSobrenome("Gates");
    notification2.setFoto("uploads/userprofile/A20CF1B8FFA84d58ad6s89qBAFE4F78C.jpg");
    notification2.setNome_cidade("Washington");
    notification2.setNome_estado("NE");
    recordSet.add(notification2);
    
    Notification notification4 =  new Notification();
    notification4.setNome("Stev");
    notification4.setSobrenome("Jobs");
    notification4.setFoto("uploads/userprofile/ADHJOD8asd121B8FFAd6s89qBAFE4F78C.jpg");
    notification4.setNome_cidade("Palo Alto");
    notification4.setNome_estado("CA");
    recordSet.add(notification4);
```

O códgo acima popula um POJO, gerando uma List<Notification> recordSet, cada instancia nesta lista será um novo Card.


### Resultado final.

Espero que tenha ajudado!

Fico a disposição para tirar dúvidas:
guilhermeborgesbastos@gmail.com

## Agredecimentos

Fábio Dexter & Guilherme Mendes.
