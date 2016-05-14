
# TinderSwipeBastos
Componente completo para quem precisa de um baralho de cartas dinamico e interativo para Android. Muito parecido com o que encontramos no Tinder. Código totalmente aberto para modificações e melhorias.

| Gif | Video |
| --- | --- |
| ![TinderSwipeBastos_animated](https://meucomercioeletronico.com/tutorial/TinderSwipeBastos_animated.gif)  | [![VIDEO](https://img.youtube.com/vi/r6qHrTARf2U/0.jpg)](https://www.youtube.com/watch?v=r6qHrTARf2U) |
  

# Georreferencimento com PHP e MySQL

Exemplo de como utilizar georreferenciamento com PHP & MySQL.
Muito útil para aplicações que necessitam encontrar ou geo localizar usuários em um determinado raio de distancia utilizando apenas a latitude e longitude armanenada no banco de dados na tabela de usuário.


## Instalação e uso

Anexo no projeto o index.php e o .sql, basta baixa-lo e configurar o banco de dados.

## Explicando o código

Recentemente me deparei com a necessidade de efetuar uma busca de usuário no banco de dados MySQL para um App android, tendo como parâmetro a localização de um determinado usuário ( latitude & longitude ). 

Neste tutorial irei mostrar como fazer tal busca no banco de dados para uma aplicação android ( via API ) ou web.

 
### Ajustando a estrutura da tabela do seu banco de dados.
Iremos fazer uma busca geo-referencial, ou seja, precisamos ter na tabela dos usuários duas colunas ( latitude & longitude ), estas duas colunas serão utilizadas em nosso cálculo.

Abaixo um exemplo básico de uma tabela de cadastro de usuários onde foi vinculado sua Latitude e Longitude. Deverá fazer o mesmo em sua tabela de usuários.
  
![alt text][logo]
[logo]: https://media.licdn.com/mpr/mpr/shrinknp_800_800/AAEAAQAAAAAAAAf1AAAAJDcwYzU4OGNiLTlmZDItNDVmNi1iODhmLTVhMWVmYTc5ZTViOQ.png "Database"

 
### Código PHP para busca geo-referencial no banco de dados:

```
//  @param: $id_usuario -> id do usuário âncora
$id_usuario = $_POST['id'];

//  @param: $alcance  -> variável em KM que deseja que a busca alcance
$alcance = 10;  //10 km

//  @param: selectRaw  -> variável que armazenará a query de geo-referencia
$selectRaw = '';

// @param: havingRaw -> variável que armazenará a query ' having ',
//que selecionará os usuários com base no @param: $alcance
$havingRaw = '';

//  aqui capturo a latitude e longitude do usuário âncora
$cadastro = \Cadastros::where('id_usuario', '=', $id_usuario)->first();
$latitude = (float)$cadastro->latitude;
$longitude = (float)$cadastro->longitude;

// populo o @param: $selectRaw com a query de geo-referencia
// esta query retorna um alias: distancia_km ( Double )
// retorna distancia do usuário âncora em relação ao referente usuário da row
$selectRaw .= '
     ( ROUND( ( ( (
          acos(
             sin( (cadastros.latitude * pi() / 180) ) * sin( (' . $latitude . ' * pi() / 180) )
             +
             cos( (cadastros.latitude * pi() / 180) ) * cos( (' . $latitude . ' * pi() / 180) )
             *
             cos( ( (cadastros.longitude - ' . $longitude . ') * pi () / 180 ) )
         )
    )  *  180 / pi() ) * 60 * 1.853159616 ), 4) ) AS distancia_km
    ,
';

// populo o @param: $havingRaw com a query having
// ela faz um IF que veriica se o usuário da row
// está dentro do raio de alcance @param: $alcance
$havingRaw .= '
     HAVING (
                   if (
                        distancia_km <= "' . $alcance . '",
                        true,
                        false
                   )
      )
';

//  @param: queryAlcance -> variável que armazenará a query geral
//  está query une todos os @param para trazer os usuários corretos
//  com base nos usuários presentes no raio de X Km
$queryAlcance = $objDB::select(
           $objDB::raw('
                    SELECT
                    ' . $selectRaw . '
                    cadastros.*, usuarios.*, cadastros.cidade id_cidade, 
                    cadastros.estado id_estado, cidade.nome nome_cidade, 
                    estado.nome nome_estado
                    FROM cadastros
                    JOIN usuarios ON usuarios.id = cadastros.id_usuario
                    LEFT JOIN cidade ON cidade.id = cadastros.cidade
                    LEFT JOIN estado ON estado.id = cadastros.estado
                    WHERE
                    usuarios.id  !=  ' . $id_usuario . '
                   ' . $havingRaw . '
         ')
);

// obs.:  o trecho da query:
// usuarios.id  !=  ' . $id_usuario . '
// exclui da listagem o próprio usuário âncora

// captura o total de usuários encontrados na geo-referencia
$totalAlcance = ($queryAlcance) ? count($queryAlcance) : 0;

if  ( $totalAlcance  > 0 ) {
          echo json_encode($queryAlcance);
}  else  {
          echo 'Nenhum usuário encontrado.';
}
```
 

### Resultado final.
Para mostrar os resultados usei o PHP da sua forma mais pura possível, utilizando o mysqli para conexão no banco de dados, veja o que o retorno trás ao enviar o parâmetro via _POST:

>  id_usuário

Que é o usuário âncora, que servirá como referencia sua lat & lng para a busca no banco de dados por usuários próximos.

Veja print do resultado trago no Json:

![alt text][result]
[result]: https://media.licdn.com/mpr/mpr/shrinknp_1000_1000/AAEAAQAAAAAAAAlsAAAAJGIxZTQ1MjNjLWYxYTEtNDg3OS1iZDU4LWMxZjFjMTY0ZTU1Yw.png "resultado"

Como podem observar o Json trouxe uma lista com os 10 usuários que estavam ate 10 km do usuário âncora. 

Espero que tenha ajudado!

Fico a disposição para tirar dúvidas:
guilhermeborgesbastos@gmail.com

## Agredecimentos

Fábio Dexter & Guilherme Mendes.
