# Leia abaixo (integration project)

WebServices feito em java com maven e framework spring boot ativo em container wildfly.

# Sobre

Projeto de integração/transferencia de pedidos, com um trabalho agendado de 10 em 10 minutos obtendo os pedidos de um webservices,
preparando esses pedidos e enviando para outro webservices, com um controle de transferencia em um arquivo JSON no servidor para que, por exemplo,
algum pedido enfrentou algum problema para ser transferido, além de não se perder será persistido até que o mesmo seja enviado com sucesso.
O mesmo se encontra em um servidor Windows no AWS, ativado em um container wildfly.

Segue o link para verificação de status da API: http://18.230.144.31:8089/integration/ws/status/verification


Implantado tambem um teste automatizado usando spring boot test com junit que o mesmo chame o trabalho acima sem trabalho agendado.
