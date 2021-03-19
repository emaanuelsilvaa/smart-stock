# SmartStock
Projeto da disciplina DIM0138 - PROJETO DETALHADO DE SOFTWARE de 2020.2

## Autores
- Yago Marques
- Camila Duarte
- Leonandro Gurgel

## Sprint 1 15/02/2021
- Adicionar/Remover/Consultar: 
	Matéria Prima
	Produto Final
	Cliente
	Fornecedor
- Realizar venda
- Consultar estoque

## Sprint 2 01/03/2021
- Alterar:
	Matéria Prima
	Produto Final
	Cliente
	Fornecedor
- Cancelar Venda
- Agendar/Cancelar encomendas
- Solicitar lista de reposição de Matéria Prima
- Solicitar lista de reposição de Produto Final
- Padrão Singleton

## Sprint 3 15/03/2021
- Análise de Lucro
- Tratamento de exceção e regras de negócio
- Implementação da GUI


## Levantamento de Requisitos
- CRUD matéria-prima
    - Sugestão: Colocar a data-de-validade para ter o controle da perecibilidade
- CRUD produtos-finais
- CRUD clientes
- CRUD fornecedores
- Consultar estoque
- Realizar/cancelar vendas
- Agendar/cancelar encomendas
- Solicitar lista de reposição de Produto Final
- Solicitar lista de reposição de Matéria Prima
- Análise de Lucro
- Tratamento de exceção e regras de negócio
- GUI

## Arquitetura do projeto
Arquitetura em Layer, onde temos as camadas GUI, BUSINESS e DATA.
Padrão Singleton em todas as classes.
Padrão Command na camada GUI.

## **IMPORTANTE** Configuração do ambiente de desenvolvimento e utilização correta do GIT
### JDK
Tenha certeza que tenha instalado em sua máquina o JDK 12.0.2, caso não tenha instalado, clique [aqui](https://www.oracle.com/br/java/technologies/javase/jdk12-archive-downloads.html)
### Adicionando projeto no Eclipse
Clone o projeto do repositório remoto, se já estiver em sua máquina, lembre-se sempre de atualizá-lo com um ``` git pull ```. Em seguida, no Eclipse, siga File > Open Projects from File System > local_do_projeto :) Feito isso, para testar o ambiente, rode o Main e veja se o Hello, world! está sendo printado.
### Usando o GIT corretamente
1. Sempre atualizar o seu repositório local antes de começar a trabalhar. Utilize o comando ```git pull```
