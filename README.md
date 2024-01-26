# FastLanguageLearning

## 1. Visão Geral do Projeto:
### Descrição:
Você precisará construir uma app, DictionMaster, que é um dicionário de inglês baseado na API gratuita Free Dictionary. A app apresenta um launch screen e na sequência chega à tela principal na qual você pode realizar uma busca no dicionário na língua apresentada (somente inglês). Ao realizar uma busca uma tela com resultado é apresentada, nela você pode tocar o áudio do termo, ver os significados e para cada significado alguns exemplos. Cada usuário tem direito a 10 buscas gratuitas por dia (desconsiderando pesquisas já feitas), quando este limite é ultrapassado uma tela de compra é apresentada para ele. O fluxo feliz termina na exibição da tela de compra e o clicar na compra sai da tela.  Um outro aspecto importante é que você precisa implementar um cache de busca, da maneira que preferir, para que buscas iguais, pelo mesmo termo, não faça uma nova requisição REST do Free Dictionary API.

### Tecnologias Utilizadas:
No desenvolvimento deste projeto, a escolha estratégica de tecnologias visou criar uma aplicação robusta e eficiente. Destacamos as principais tecnologias adotadas:

#### Kotlin:
- O Kotlin foi a linguagem central do projeto, proporcionando uma base sólida com sua sintaxe concisa, segurança nula (null safety) e interoperabilidade fluida com o Java. Essas características contribuíram para um código mais expressivo, legível e resiliente.
#### Retrofit (Comunicação com a API):
- A interação com a API foi aprimorada pela utilização do Retrofit, uma biblioteca reconhecida por sua eficiência na construção de clientes HTTP voltados para APIs RESTful. A simplicidade na definição de endpoints e no tratamento de respostas facilitou a integração efetiva do aplicativo com a API.
#### OkHttpClient com Retrofit (Cache de API):
- O gerenciamento inteligente de dados foi alcançado pela implementação do sistema de cache, utilizando a combinação do OkHttpClient com o Retrofit. Essa abordagem possibilitou o armazenamento temporário local de respostas de requisições HTTP, reduzindo a necessidade de solicitações repetitivas à API e otimizando o desempenho geral do aplicativo.
#### Room (Persistência de Dados Locais):
- Para contabilizar as requisições realizadas ao longo do dia, incorporou-se o Room, uma biblioteca de persistência oferecida pelo Android. O Room facilitou a criação e a administração eficiente do banco de dados local, contribuindo para a precisão e a confiabilidade na contagem de requisições.
- 
## 2. Arquitetura:

### Padrão de Arquitetura - MVVM:

O projeto adota o padrão de arquitetura MVVM (Model-View-ViewModel), proporcionando uma estrutura organizada e eficiente para o desenvolvimento. A arquitetura é organizada seguindo a hierarquia abaixo:

#### 1. fastlanguagelearning:
Pasta raiz do projeto.

#### 2. activity:

Contém as funções Kotlin relacionadas às atividades do aplicativo. Aqui, as lógicas de apresentação e interação do usuário são implementadas, seguindo os princípios do MVVM.

#### 3.  api:

Responsável pela configuração dos endpoints utilizados para a comunicação com a API. Esta camada isola a lógica de comunicação remota, facilitando a manutenção e a alteração das fontes de dados.

#### 4. dao:

A camada de acesso a dados (DAO - Data Access Object) é dedicada à interação com o banco de dados local, seguindo as boas práticas do MVVM. Aqui, são definidas as operações de acesso aos dados, garantindo a separação adequada entre a lógica de negócios e a persistência de dados.

#### 5. db:

Esta pasta abriga a configuração do banco de dados local. É nesse contexto que são definidas as instâncias e configurações do Room, proporcionando uma integração eficiente com a camada DAO.

#### 6. entity:

Contém as entidades do banco de dados local. As entidades são classes que representam as tabelas do banco de dados e são essenciais para a persistência de dados utilizando o Room.
#### 7. models:

As data classes de resposta (models) são organizadas nesta pasta. Elas representam a estrutura dos dados obtidos das requisições à API e são mapeadas para objetos utilizados na lógica de apresentação.
#### 8. util:

A pasta util engloba classes e funções utilitárias que podem ser compartilhadas em diferentes partes do projeto.
#### 9. res:

A pasta res contém recursos utilizados no aplicativo, incluindo layouts (layout), valores (values) e outros recursos visuais e textuais.
#### 10. layout:

Armazena os arquivos XML que definem a estrutura e o design das interfaces do usuário.
#### 11. values:

Contém arquivos XML que definem valores constantes, como strings, cores e estilos, utilizados em todo o aplicativo.

A adoção do padrão MVVM, juntamente com a estrutura organizada do projeto, visa separar as responsabilidades de forma clara, facilitando a manutenção, testabilidade e escalabilidade do código. Essa arquitetura promove uma divisão adequada entre a apresentação de dados, a lógica de negócios e o acesso aos dados, contribuindo para um desenvolvimento mais modular e sustentável.



## 3. Classes Criadas no Projeto
Para a construção do projeto, foram criadas as data classes SearchResponse, Definition, Phonetic e Meaning com o objetivo de estruturar e encapsular os dados de retorno provenientes da API. Essas classes fornecem uma representação organizada e coesa das informações relacionadas à busca por significados de palavras, facilitando o processamento e a manipulação desses dados dentro do contexto do projeto.
![image](https://github.com/LeonardoWAP/FastLanguageLearning/assets/57870208/8e51549a-ce83-420f-8590-c58b4c8a28bf)

## 4. Entidades 
Para a construção do projeto, foi criada a entidade RequestCount com o propósito de registrar diariamente a quantidade de requisições feitas pelo usuário no aplicativo. Quando uma requisição é realizada com sucesso, uma instância da entidade é criada, armazenando a data da requisição no formato Long. À medida que o usuário efetua outras requisições no mesmo dia, o campo count é atualizado. Dessa forma, o banco de dados mantém a informação sobre a quantidade total de requisições realizadas em um determinado dia.

![image](https://github.com/LeonardoWAP/FastLanguageLearning/assets/57870208/323a5cf1-9b50-4fe3-a9c4-4b9492758acc)



## Foto das telas do projeto

<img src="https://github.com/LeonardoWAP/FastLanguageLearning/assets/57870208/6c7d452c-cac0-46a0-bcdf-17a4e9821977" width="50%">

<img src="https://github.com/LeonardoWAP/FastLanguageLearning/assets/57870208/6d454402-bfd3-4577-ba1f-1021385cbe41" width="50%">

<img src="https://github.com/LeonardoWAP/FastLanguageLearning/assets/57870208/cb47737f-0833-4755-8473-c3f6cd90aa90" width="50%">

<img src="https://github.com/LeonardoWAP/FastLanguageLearning/assets/57870208/4fffd0e7-b6f6-496b-b9b6-969ccc1bb91f" width="50%">



