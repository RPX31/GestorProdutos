# GestorProdutos — Sistema de Microsserviços

Sistema de gerenciamento de produtos desenvolvido com **Java** e **Spring Boot**, utilizando uma arquitetura robusta de microsserviços para separar as responsabilidades de categorias, marcas, produtos e controle de estoque.

O projeto foi construído com foco em organização de código, comunicação via clientes REST, aplicação de regras de negócio estritas, tratamento centralizado de exceções, auditoria de movimentações e testes automatizados.

---

## 🏗️ Arquitetura do Projeto

O ecossistema está dividido em quatro microsserviços independentes, cada um com seu próprio banco de dados (garantindo isolamento arquitetural):

| Microsserviço | Responsabilidade | Porta |
|---|---|---|
| **GestorProdutos** | Gerenciamento de categorias | `8080` |
| **Produto Service** | Gerenciamento de produtos e integração | `8082` |
| **Marca Service** | Gerenciamento de marcas | `8083` |
| **Estoque Service** | Controle de estoque, movimentações e auditoria | `8084` |

---

## 🚀 Funcionalidades por Microsserviço

### 1. GestorProdutos (Categorias)
- Cadastro, consulta (geral e por ID), atualização e exclusão de categorias.
- Validação estrita de dados (nome obrigatório, limites de caracteres).
- Tratamento global de erros e respostas padronizadas.

### 2. Marca Service
- Gerenciamento completo de marcas de produtos (CRUD).
- Validação de dados de entrada e tratamento de exceções.

### 3. Produto Service
- Gerenciamento de produtos (`id`, `nome`, `preco`, `descricao`, `categoriaId`, `marcaId`).
- A responsabilidade de controle de quantidade foi isolada para o **Estoque Service**.
- **Integração de Estoque Inicial:** No momento do cadastro de um produto, o serviço envia a quantidade inicial informada para o Estoque Service via `RestClient`.
- Paginação, listagem detalhada e rotas auxiliares para verificação de vínculos.

### 4. Estoque Service
- **Estoque Inicial e Vendas:** 
  - Criação automática de estoque associada ao produto.
  - Implementação do fluxo de **retirada de estoque** para baixa por vendas realizadas.
- **Tipos de Movimentação (`TipoMovimentacao`):**
  - `ENTRADA`, `SAIDA`, `AJUSTE`.
- **Auditoria e Rastreabilidade:** Cada movimentação registra ID, tipo, quantidade, saldo anterior, saldo posterior, motivo e data/hora.
- **Regras de Negócio e Validações:**
  - Impedir movimentações de saída ou vendas superiores ao saldo disponível (`BusinessRuleException` / HTTP 400).
  - Bloquear criação de estoque para produtos inexistentes (HTTP 404).
  - Integração resiliente com o Produto Service (tratamento para indisponibilidade via HTTP 503).

---

## 🧪 Testes Automatizados

O projeto conta com testes unitários implementados com **JUnit 5** e **Mockito**:

- **`EstoqueServiceTest`:** Valida cenários críticos do estoque, cobrindo com sucesso:
  - Realização de retirada/venda válida com saldo suficiente.
  - Lançamento e propagação de `BusinessRuleException` em cenários de estoque insuficiente.
  - Validação de quantidades inválidas.
- **`ProdutoServiceTest`:** Simula o comportamento dos clientes REST de marcas e categorias e testa o fluxo de criação e exclusão de produtos.

---

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.4.x**
- **Spring Web** & **Spring Data JPA**
- **Spring RestClient**
- **Bean Validation** & **Hibernate**
- **H2 Database** (banco em memória para ambiente de desenvolvimento/testes)
- **Maven**
- **JUnit 5** & **Mockito**
- **Insomnia** (para testes manuais de API)

---

## ⚙️ Como Executar o Projeto Localmente

Recomenda-se abrir cada microsserviço em uma instância separada da sua IDE (ex: IntelliJ IDEA).

### Ordem Recomendada de Inicialização
1. `GestorProdutos` (Porta `8080`)
2. `Marca Service` (Porta `8083`)
3. `Produto Service` (Porta `8082`)
4. `Estoque Service` (Porta `8084`)

### Comandos de Execução (via Terminal)

**No Windows:**
```cmd
# Para executar a aplicação
mvnw.cmd spring-boot:run

# Para rodar os testes automatizados
mvnw.cmd clean test

# Para compilar o projeto
mvnw.cmd clean compile

# Para executar a aplicação
./mvnw spring-boot:run

# Para rodar os testes automatizados
./mvnw clean test

# Para compilar o projeto
./mvnw clean compile

Repositórios dos Microsserviços
GestorProdutos: GitHub - GestorProdutos

Produto Service: GitHub - produto-service

Marca Service: GitHub - marca-service

Estoque Service: (Repositório dedicado em desenvolvimento / a ser adicionado em breve)
