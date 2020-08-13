# Crud Angular + Spring 

#### Foram criadas 3 aplicações para atenter o objetivo:

* projeto-backend-ia:
  * Contém a criação do Banco de Dados em memória do tipo H2.
  * Usuário administrador é criado no deploy da aplicação.
    * nome de usuário: **admin**
    * senha: **password**
  * Sistema de envio de tokens para acessar endpoints do sistema utilizando JWT.
  * Endpoints para o CRUD e para o envio de e-mails
  * Sessão do usuário sendo guardada no Redis
  * Utilização do Swagger para criar uma documentação da aplicação
    * url: http://localhost:8080/swagger-ui.html

* projeto-frontend-ia:
  * Cria interface para acesso das funcionalidades do CRUD.
  * Token criado pelo JWT é persistido como um Header no Frontend, que é enviado com todo request para a verificação de autenticação.
  * Criação de validações de dados no Frontend.

* email-job:
  * Aplicação que encaminha os e-mails da fila de forma assíncrona.
  * Realiza uma verificação no Database em memória para enviar o e-mail para Usuários e Admin selecionado
    * A demonstração do Envio é feito via console com Logger.


## Configuração do Projeto
### Necessário que a máquina possua instalados:
- java 8
- maven
- docker
- docker-compose
- npm
- node.js

### Clonar projeto:
```sh
git clone https://github.com/tliradiniz/projetoIA.git
```

### Angular:
- Para utilizar o angular:
```sh
npm install -g @angular/cli
```

### Arquivos de configuração:
- A configuração das propriedades no sistema é feita a partir de um arquivo chamado **application.properties** no módulo **projeto-backend-ia** e também outro no módulo **email-job**.
- Ambos possuem as configurações do DB H2 e configurações do RabbitMQ.
- **projeto-backend-ia** possui ainda propriedades de configuração do Redis.
- **projeto-backend-ia** porta: 8080.
- **email-job** porta: 8085.
- Foi utilizada a biblioteca Lombok, que pode necessitar de uma instalação em alguns casos. Caso seja necessário segue um link explicativo: https://howtodoinjava.com/automation/lombok-eclipse-installation-examples/

## Execução

- Importar maven project **projeto-backend-ia** e **email-job** pela IDE
- Na raiz da pasta **projeto-frontend-ia**, executar o seguinte comando
```sh
npm install
```
- Em seguida subir o servidor frontend:
```sh
ng serve
```

- Iniciar containers pelo compose:
```sh
docker-compose up
```
- Executar os módulos com spring boot:
  * projeto-backend-ia
  * email-job


### Walkthrough
- Com os dois módulos backend rodando e o servidor frontend também disponível, acessar o endereço: http://localhost:4200/
- Ao ser iniciado, deve-se logar no sistema com o usuário admin que foi criado no momento do deploy da aplicação.
- Ao ser logado, o Usuário poderá ver três abas:
    * Registro de Usuários
    * Lista de Usuários
    * Enviar E-mail
- Na aba **Registro de Usuários** é possível se adicionar um novo usuário. Foram feitas algumas validações no Frontend e também validações na entrada de dado no banco para evitar que alguns campos fiquem null.
- Nenhum dos endpoints é acessável caso você não esteja autenticado. Caso seja tentado um acesso direto pela URL para uma tela, você é redirecionado para a tela inicial.
- Na Aba **Lista de Usuários** são mostrados todos os usuários cadastrados e são implementadas as funcionalidades Read, Update e Delete do CRUD. Ao ser efetuado um update, o campo updatedDate do usuário será atualizado no banco para o dia da data de atualização.  **OBS**:Apenas O usuário Admin pode modificar a senha e apenas o Usuário admin pode deletar um usuário.
- Na Aba **Enviar E-mail** é mostrada novamente uma listagem de todos os usuários para que seja selecionado para quem será enviado o e-mail. Selecionado o Usuário, é mostrada então uma tela para envio da mensagem. Ao ser pressionado enviar mensagem, ela irá para uma Fila no rabbitMQ e será então processada pelo job de e-mail.
- No **email-job**, ao ser recebida uma mensagem, ela será enviada para o usuário que foi pedido e também para todos os usuários que possuam o cargo de administrador. Encontrados os Admins e o nome de usuário do destinatário, uma mensagem no console será mostrada para indicar a transferência da mensagem.
