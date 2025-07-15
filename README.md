# 📦 ProdutoManager

Sistema completo de gerenciamento de produtos com categorias e marcas, construído com **Java + Spring Boot** no backend e **HTML/CSS/JavaScript + Thymeleaf** no frontend. O projeto utiliza **H2 Database** em memória para persistência e aplica validações diretamente nas entidades.

---

## 🚀 Tecnologias Utilizadas

### 🔧 Backend
- **Java 21**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **Spring Web**
- **Lombook**
- **Docker**
- **Bean Validation (Jakarta Validation)**

### 🎨 Frontend
- **HTML5**
- **CSS3**
- **JavaScript**
- **Thymeleaf** (template engine para renderização server-side)

### 🗃️ Banco de Dados
- **H2 Database** (em memória, com console web) na construção da api
- **Postegress** no deploy da aplicação

---

## 🧩 Funcionalidades Principais

- ✅ **Cadastro de Produtos**
  - Nome, descrição, preço e quantidade
  - Associação com **Categoria** e **Marca**
  - Validações nos campos (ex: nome com no mínimo 3 e no máximo 50 caracteres)

- ✅ **Cadastro de Categorias**
  - Nome da categoria
  - Listagem e associação com produtos

- ✅ **Cadastro de Marcas**
  - Nome da marca
  - Listagem e associação com produtos

- ✅ **Listagem e Edição**
  - Listagem de todos os produtos com marca e categoria
  - Edição e exclusão de produtos

- ✅ **Validações**
  - Campos obrigatórios
  - Tamanhos mínimos e máximos
  - Valores numéricos mínimos

- ✅ **Interface Web Responsiva**
  - Criada com HTML/CSS
  - Comportamentos básicos usando JavaScript
  - Templates Thymeleaf

---

## 🧪 Validações nas Entidades

As validações são aplicadas diretamente nas classes de entidade com **anotações**, como:

```java
@Size(min = 3, max = 50)
@NotNull
@DecimalMin("0.01")
@Min(1)


💻 Como Executar
Clone o repositório:git clone https://github.com/RPX31/GestorProdutos.git

Acesso em produção
link: https://gestorprodutos.onrender.com

