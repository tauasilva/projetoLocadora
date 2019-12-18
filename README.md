# Locadora

Projeto desenvolvido para 4all que consiste em um mini sistema para uma locadora.

## Getting Started

	Este projeto foi desenvolvido com Spring Boot, MySQL, hibernate, spring-boot-starter-security,JSON Web token e Swagger.
	Utiliza um sistema de autenticação é no padrão Stateless, ou seja o sistema não irá armazenar a sessão.
	As dependências são gerenciadas através do MAVEN.

### Prerequisites

	Jdk 1.8
	Spring Tool Suite 4 
	MySQL
	MySqlWorkBench


### Installing

	Através do do MySqlWorkBench, importe a base de dados atráves do menu Server - Data import. Se já possuir uma base de dados configurada também é possível apenas apontar no arquivo de propriedades do projeto que o Hibernate irá criar todas as tabelas automaticamente.

	Após configurado o database, vamos importar o projeto dentro do Eclipse Spring Tool. Para isso após abrir o Eclipse, vá em File -> Import, selecione o projeto, aponte a versão do JDK caso necessário, o Maven irá baixar automaticamente todas as dependências.

	Caso seu banco não esteja com o padrão que foi apontado no projeto, será necessário informar a nova conexão no arquivo "application.properties"

	Após importar o projeto basta executar local e acessar a URL http://localhost:8080/swagger-ui.html#/



### Test


	Para testar se nosso projeto esta no ar e comunicando com a base, podemos fazer um teste básico sem o front-end.
	O Swagger é um framework utilizado para documentação de Web Services REST, porém neste projeto ele foi configurado para aceitar requisições com o Bearer Token.

	Se você importou o banco de dados anexo a raiz do projeto, já existem algumas informações previamente cadastradas.

	Vamos começar solicitando um Token para utilizar nos demais testes.
	Clique em "autenticacao-controller"

	Logo após clique em "POST / Auth"

	Agora você tem acesso a uma documentação do endpoint chamado Auth.

	Como parâmetros necessários, exemplos, códigos de retorno.

	Vamos clicar em "Try it out"

	No campo form - Example value como login vamos utilizar "adm" e senha "123456"

	{
  		"login": "adm",
  		"senha": "123456"
	}

	Após clique em execute.

	em Response body
	Você terá o Token criptografado com seu Login/Senha e válidade de 24 horas.

	Copie o token e vamos testar uma outra requisição.

	Clique em POST / Auth

	e logo após clique em filme-controller

	Você vai ver todos os endpoint relacionados a filme ( Cadastrar/Atualizar/Deletar/Pesquisar/ListagemPorDiretor/ListaPorNome)

	Clique em GET/Filmes

	depois clique em "Try it Out"

	Em "Header para Token JWT" vamos colocar o token que requisitamos anteriormente.
	Porém precisamos Colocar antes o padrão "Bearer"
	Exemplo

	Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUEkgUkVTVCBUSTRHTyIsInN1YiI6IjIiLCJpYXQiOjE1NzY3MTEzMDUsImV4cCI6MTU3Njc5NzcwNX0.tud_0rQqrEQ5C_yowLoSeWgFOb7kva0YildbLn37g_0

	Se você clicar em Execute terá a listagem de Filmes.

	O Retorno além da listagem de Filmes também possui informações para o Front fazer a paginação do conteúdo.

	Seguindo esse padrão de colocar o Token na requisição você poderá testar qualquer endpoint.


	Sistema de Locação/Devolução de Filmes:

	
	Locar um Filme:
	
		Basta fazer um POST para o endpoint Movimentacoes/locar passando o ID do Filme desejado. O registro ficará vinculado ao usuário do Token informado na requisição.

		Para devolver um filme faça um PUT para o endpoint Movimentacoes/devolver passando o ID do Filme que deseja devolver.



## Authors


* Tauã Fagundes da Silva (https://github.com/tauasilva)


