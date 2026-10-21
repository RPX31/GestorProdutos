GestorProdutos
API REST para gerenciamento de produtos, categorias e marcas, desenvolvida com Java e Spring Boot. O projeto foi construído para praticar desenvolvimento backend, organização em camadas e comunicação entre serviços independentes.
Visão geral
O sistema é organizado em serviços responsáveis por diferentes partes do domínio:
- Produto: cadastra, consulta, atualiza e remove produtos.
- Marca: gerencia as marcas associadas aos produtos.
- Categoria: gerencia as categorias dos produtos.
  O serviço de Produto consulta os serviços de Marca e Categoria por HTTP para validar referências e montar respostas detalhadas. Assim, o produto mantém os identificadores das referências e utiliza os serviços responsáveis para obter os respectivos dados.
  Arquitetura
  Cliente HTTP (Insomnia)
  |
  v
  Produto Service :8082
  /       \
  v         v
  Marca Service  Categoria Service
  :8083           :8080
  As portas acima correspondem à configuração local utilizada no desenvolvimento. Caso os arquivos de configuração tenham sido alterados, ajuste as URLs de acordo com o ambiente.

Tecnologias
- Java 21
- Spring Boot 3.4.5
- Spring Web / APIs REST
- Spring Data JPA e Hibernate
- Jakarta Bean Validation
- Maven
- Lombok
- Banco de dados configurado por serviço
- Insomnia para testes manuais das requisições HTTP
  Funcionalidades
  Produtos
- Cadastrar produto associado a uma marca e uma categoria.
- Listar produtos com paginação.
- Consultar produto por ID, incluindo os dados de marca e categoria.
- Atualizar produto.
- Excluir produto.
- Verificar se existem produtos associados a determinada categoria ou marca.
- Validar dados de entrada e tratar falhas na comunicação com serviços externos.
  Marcas e categorias
- Operações de cadastro, consulta, atualização e exclusão.
- Consulta por ID.
- Integração com o serviço de Produto por meio de chamadas REST, quando aplicável.
  Endpoints
  Produto Service — http://localhost:8082
  Método	Endpoint	Descrição
  GET	/produtos	Lista produtos com paginação
  GET	/produtos/{id}	Busca um produto por ID
  POST	/produtos	Cadastra um produto
  PUT	/produtos/{id}	Atualiza um produto
  DELETE	/produtos/{id}	Exclui um produto
  GET	/produtos/categoria/{categoriaId}/existe	Verifica se há produtos associados à categoria
  GET	/produtos/marca/{marcaId}/existe	Verifica se há produtos associados à marca


A paginação do Spring Data pode ser usada com parâmetros como page, size e sort. Exemplo:
GET http://localhost:8082/produtos?page=0&size=10&sort=nome,asc
Marca Service — http://localhost:8083
Método	Endpoint	Descrição
GET	/marcas	Lista marcas
GET	/marcas/{id}	Busca uma marca por ID
GET	/marcas/buscar?nome={nome}	Busca marcas pelo nome
POST	/marcas	Cadastra uma marca
PUT	/marcas/{id}	Atualiza uma marca
DELETE	/marcas/{id}	Exclui uma marca


Categoria Service — http://localhost:8080
Método	Endpoint	Descrição
GET	/categorias	Lista categorias
GET	/categorias/{id}	Busca uma categoria por ID
POST	/categorias	Cadastra uma categoria
PUT	/categorias/{id}	Atualiza uma categoria
DELETE	/categorias/{id}	Exclui uma categoria


Confirme os endpoints de Marca e Categoria nos respectivos controllers antes de publicar, pois eles podem variar conforme a versão atual de cada serviço.

Exemplo de requisição
Cadastrar um produto
POST http://localhost:8082/produtos
Content-Type: application/json
{
"nome": "Teclado mecânico",
"preco": 249.90,
"descricao": "Teclado mecânico USB",
"quantidade": 15,
"categoriaId": 1,
"marcaId": 1
}
Os valores de categoriaId e marcaId precisam corresponder a registros existentes nos serviços de Categoria e Marca.
Exemplo de resposta de erro de validação
Ao enviar um preço negativo, a API deve responder com HTTP 400 Bad Request e uma mensagem que explique a validação:
{
"timestamp": "2026-10-21T17:14:08.8076098",
"message": "O preço do produto não pode ser negativo",
"error": "Erro de validação",
"status": 400
}
O formato exato do corpo da resposta depende do tratamento de exceções configurado na versão em execução.
Validações e tratamento de erros
O serviço de Produto utiliza validação de entrada e tratamento centralizado de exceções. Entre os casos tratados estão:
HTTP	Situação
400 Bad Request	Dados inválidos ou regra de negócio violada, como preço negativo ou nome obrigatório ausente
404 Not Found	Produto, marca ou categoria não encontrado
503 Service Unavailable	Indisponibilidade na comunicação com os serviços de Marca ou Categoria
409 Conflict	Violação de integridade dos dados, quando aplicável
500 Internal Server Error	Erro inesperado no servidor


As respostas de erro são centralizadas no GlobalExceptionHandler, para evitar que detalhes internos da aplicação sejam enviados diretamente ao cliente.
Como executar localmente
Pré-requisitos
- JDK 21 instalado.
- Maven instalado ou Maven Wrapper disponível no repositório.
- Configuração do banco de dados de cada serviço.
- Os serviços de Marca e Categoria disponíveis para testar o fluxo completo do serviço de Produto.
1. Configure os serviços
   Confira o application.properties ou application.yml de cada aplicação. Configure URL, usuário e senha do banco de dados e as URLs usadas pelo serviço de Produto para acessar Marca e Categoria.

Não publique senhas, tokens ou outras credenciais no repositório. Prefira variáveis de ambiente para informações sensíveis.
2. Inicie Marca e Categoria
   Abra um terminal no diretório de cada serviço e execute:
   ./mvnw spring-boot:run
   No Windows, se o Maven Wrapper estiver presente:
   .\mvnw.cmd spring-boot:run
   Se o projeto não tiver Maven Wrapper, use Maven instalado:
   mvn spring-boot:run
3. Inicie Produto
   Com Marca e Categoria em execução e suas URLs configuradas, execute o mesmo comando no diretório do serviço de Produto.
   Se os serviços estiverem em repositórios separados, clone cada repositório e execute cada aplicação em seu próprio diretório. Os comandos devem ser executados na pasta que contém o respectivo pom.xml.

Testes manuais
As requisições podem ser realizadas pelo Insomnia. Para validar o fluxo principal:
1. Inicie os serviços de Marca e Categoria.
2. Cadastre ou consulte uma marca e uma categoria para obter IDs válidos.
3. Envie um POST /produtos com esses IDs.
4. Consulte o produto com GET /produtos/{id} e confira os dados detalhados.
5. Teste um preço negativo e confira a resposta 400.
6. Teste um ID inexistente e confira a resposta 404.
7. Pare temporariamente Marca ou Categoria e teste como o serviço de Produto responde à indisponibilidade externa.
   Organização do código
   O projeto utiliza uma separação por responsabilidades, com componentes como:
- Controller: recebe requisições HTTP e define as respostas.
- Service: concentra regras de negócio e coordena operações.
- Repository: acessa os dados persistidos com Spring Data JPA.
- Entity: representa os dados persistidos.
- DTOs: definem os dados recebidos e devolvidos pela API.
- REST clients: realizam chamadas aos serviços externos.
- Exception handling: centraliza o mapeamento de erros para respostas HTTP.
  Objetivos de aprendizagem
  Este projeto permite praticar:
- Criação e consumo de APIs REST.
- Arquitetura em camadas e separação de responsabilidades.
- Spring Data JPA e paginação.
- DTOs e validação de entrada.
- Regras de negócio e tratamento global de exceções.
- Comunicação HTTP entre serviços.
- Tratamento de indisponibilidade de dependências externas.
- Testes manuais de cenários válidos e inválidos com Insomnia.
  Autor
  Rodrigo Oliveira

Projeto de estudo e portfólio voltado ao desenvolvimento backend com Java e Spring Boot.