<p align="center">
  <img src="imagens/logopadaria.webp" alt="Logo do Sistema" width="180">
</p>

<h1 align="center">Sistema de Gestão para Rede de Padarias</h1>

<p align="center">
Projeto desenvolvido para a disciplina de <strong>Programação Orientada a Objetos</strong><br>
Universidade Federal da Paraíba (UFPB)
</p>

<p align="center">
<img src="https://img.shields.io/badge/Java-21-orange">
<img src="https://img.shields.io/badge/Arquitetura-MVC-blue">
<img src="https://img.shields.io/badge/Banco-SQLite-003B57">
<img src="https://img.shields.io/badge/Status-Em%20Evolução-brightgreen">
<img src="https://img.shields.io/badge/UFPB-APS-success">
</p>

---

# 📖 Projeto de Análise e Projeto de Sistemas - UFPB

## Sobre o Projeto

Somos alunos do curso de **Sistemas de Informação** da **Universidade Federal da Paraíba (UFPB)**.

Este projeto foi desenvolvido na disciplina de **Programação Orientada a Objetos**, ministrada pelo **Professor Dr. Dorgival Netto**, com o objetivo de aplicar conceitos de Engenharia de Software, Arquitetura em Camadas, Modelagem UML e Padrões de Projeto na construção de um sistema de gestão para uma rede de padarias.

---

# 🎯 Visão Geral do Projeto

O projeto tem como objetivo desenvolver um **Sistema de Gestão para uma Rede de Padarias**, permitindo centralizar o gerenciamento das unidades da rede e melhorar o controle dos recursos utilizados em cada uma delas.

O sistema busca oferecer:

- Controle de estoque;
- Cadastro e gerenciamento de insumos;
- Registro de movimentações de entrada e saída;
- Controle das unidades da rede;
- Gerenciamento de demandas de reposição;
- Redução de desperdícios;
- Maior organização das informações.

---

# ✨ Funcionalidades

- ✅ Cadastro de Unidades
- ✅ Cadastro de Insumos   
- ✅ Adição de Insumos ao Estoque
- ✅ Registro de Movimentações (Entrada e Saída)
- ✅ Criação de Demandas de Reposição
- ✅ Consulta de Estoque por Unidade
- ✅ Listagem de Demandas de Reposição
- ✅ Consulta de Unidade
- ✅ Consulta de Insumo
- 🔄 Persistência de dados utilizando SQLite

---

# 🏗 Arquitetura do Projeto

O sistema foi desenvolvido utilizando **Arquitetura em Camadas (Layered Architecture)**, separando as responsabilidades da aplicação para facilitar manutenção, escalabilidade e organização do código.

```text
Controller
     │
     ▼
Service
     │
     ▼
Repository
     │
     ▼
SQLite

Model representa as entidades do sistema.
View representa a interface em console responsável pela interação com o usuário.
```

---

# 🧩 Padrões de Projeto Utilizados

O sistema utiliza alguns padrões de projeto para melhorar sua organização e manutenção.

- **MVC (Model-View-Controller):** separação entre interface, regras de negócio e dados.
- **Factory:** responsável pela criação das implementações dos repositórios.
- **Strategy:** utilizada para representar os diferentes tipos de movimentação do estoque (Entrada e Saída).

---

# 📂 Organização do Projeto

```text
src
│
├── controller
├── factory
├── model
├── repository
├── service
├── strategy
├── view
└── Main.java
```

---

# 📦 Principais Classes

- Unidade
- Insumo
- Estoque
- Movimentacao
- DemandaReposicao

---

# 🔄 Fluxo Básico do Sistema

O sistema é utilizado por meio de um menu em console com as seguintes opções:

```text
1 - Cadastrar Unidade
2 - Consultar Unidade
3 - Cadastrar Insumo
4 - Consultar Insumo
5 - Adicionar Insumo ao Estoque
6 - Registrar Movimentação (Entrada/Saída)
7 - Criar Demanda de Reposição
8 - Consultar Estoque de uma Unidade
9 - Listar Demandas de Reposição
0 - Sair
```

---

# 👨‍💻 Equipe

| Foto | Nome | E-mail |
|------|------|------|
| <img src="imagens/arthur.jpeg" width="100"> | Arthur Neto Coutinho da Silva | arthur.coutinho@dcx.ufpb.br |
| <img src="imagens/joao.jpeg" width="100"> | João Alisson de Oliveira Freitas | joao.freitas@dcx.ufpb.br |
| <img src="imagens/kaany.jpeg" width="100"> | Kaany Araújo Feitosa Rolin | kaany.rolin@dcx.ufpb.br |
| <img src="imagens/maria.jpeg" width="100"> | Maria Gabriella de Oliveira Barbosa | maria.oliveira@dcx.ufpb.br |

---

# 🛠 Tecnologias Utilizadas

- Java 21
- SQLite
- IntelliJ IDEA
- Git
- GitHub
- UML
- Notion
- Draw.io
- Programação Orientada a Objetos (POO)
- Arquitetura MVC

---

# 📚 Artefatos do Projeto

- ✅ Criação da Página no GitHub
- ✅ Compreensão do Sistema
- ✅ Definição da Arquitetura
- ✅ Modelagem de Classes
- ✅ Padrões de Projeto
- ✅ Diagramas de Sequência
- ✅ Implementação

---

# 📑 Documentação

| Documento | Entrega |
|------------|----------|
| 📁 [Repositório no GitHub](https://github.com/Arthurne/Projeto-APS) | Etapa 00 |
| 📁 [Compreensão do Sistema](https://app.notion.com/p/Sistema-para-Rede-de-Padarias-375df68436a5801abe2fc6e4240fbc95?p=375df68436a580a5b0dce445e2f3371f&pm=s) | Etapa 01 |
| 📁 [Definição da Arquitetura](https://app.notion.com/p/Sistema-para-Rede-de-Padarias-375df68436a5801abe2fc6e4240fbc95?p=375df68436a5808d930bdaff404cfc1f&pm=s) | Etapa 02 |
| 📁 [Modelagem de Classes](https://app.notion.com/p/Sistema-para-Rede-de-Padarias-375df68436a5801abe2fc6e4240fbc95?p=375df68436a5801b85a8f1962e3109b9&pm=s) | Etapa 03 |
| 📁 [Padrões de Projeto](https://app.notion.com/p/Sistema-para-Rede-de-Padarias-375df68436a5801abe2fc6e4240fbc95?p=397df68436a5801bb1afca6b117e669d&pm=s) | Etapa 04 |
| 📁 [Diagramas de Sequência](https://app.notion.com/p/Sistema-para-Rede-de-Padarias-375df68436a5801abe2fc6e4240fbc95?p=3b3df68436a580cba996d15a77b201aa&pm=s) | Etapa 05 |

---

# 🚀 Como Executar

Clone o repositório:

```bash
git clone https://github.com/Arthurne/Projeto-APS.git
```

Em seguida:

1. Abra o projeto no IntelliJ IDEA.
2. Configure o JDK 21 (ou versão compatível).
3. Execute a classe `Main.java`.
4. Utilize o menu do sistema para acessar as funcionalidades disponíveis.

---

# 📄 Licença

Este projeto foi desenvolvido exclusivamente para fins acadêmicos na disciplina de **Programação Orientada a Objetos** da **Universidade Federal da Paraíba (UFPB)**.
