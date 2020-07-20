# TodoMovies5

O aplicativo simboliza a tela de detalhes de um aplicativo que lista todos os filmes disponíveis. Ele mostra detalhes de um determinado filme, assim seus filmes similares.
O filme escolhido para este aplicativo foi: Tenacious D: The Pick of Destiny!

Ao abrir o aplicativo, verá os detalhes do filme escolhido na tela inicial da aplicação, e se arrastar o dedo para cima verá a lista de todos os filmes similares.

Além disso, o aplicativo possui um botão de like para demonstrar seu amor ao filme e colocá-lo como favorito.

## Algumas screenshots
![light](https://github.com/jsouza678/todomovies5/blob/master/screenshots/light.png)
![dark](https://github.com/jsouza678/todomovies5/blob/master/screenshots/dark.png)
![collapsed](https://github.com/jsouza678/todomovies5/blob/master/screenshots/collapsed.png)
![loading](https://github.com/jsouza678/todomovies5/blob/master/screenshots/loading.png)

## Ambiente de instalação
* 1: Instale o Android Studio;
* 2: Abra a aplicação;
* 3: Sincronize o projeto;
* 4: Rode o aplicativo em um simulador ou em um device externo.

## API
<p>A API utilizada é a TheMovieDB.</p>
(https://developers.themoviedb.org/3)

## Automação
Ktlint - a task valida se o padrão do código está de acordo com o lint. 
O `./gradlew ktlint` realiza a verificação de todos os componentes do projeto, e retorna o resultado.

KtlintFormat - esta tarefa modifica o código para que ele siga o padrão do lint. 
O `./gradlew ktlintFormat` roda uma rotina que formata o código de acordo com o máximo que o lint pode fazer de modificações para que o código esteja no seu padrão.

## Arquitetura
 A aplicação busca o desacoplamento e a escalabilidade em sua arquitetura, fazendo uso do Clean Architecture e do MVVM com Modularização.

 ## Boas práticas.
 Seguindo os conceitos do Clean Code, o nome das variáveis da aplicação está descritivo, além do nome dos testes seguindo o padrão recomendado 'GIVEN_WHEN_THEN'.

 ## Principais dependências

**Coroutines** - _lidando com threads e assincronismo_
 <p>Abordagem sugerida pela Google e com um bom funcionamento com o Live Data, faz bom uso das threads do dispositivo, melhorando a performance da aplicação.</p>

**Room** - _persistência de dados_
 <p>Camada de abstração sobre o SQLite, o Room é um facilitador para persistir dados no banco do aparelho. 
 É importante ressaltar que a utilização do Room e Coroutines necessita de uma forma de verificar as queries do banco de dados, já que elas devem ser feitas de forma async.
 O LiveData foi utilizado neste caso para tornar as consultas reativas, e permitirem a execução fora da main thread. (Dispatchers.Main, e sem o problemático .allowMainThreadQueries).

**Retrofit** - _requisições HTTP_
 <p>Retrofit é a biblioteca mais difundida por encapsular e lidar com requisições HTTP, além de possuir uma fácil implementação.</p>
 
**Material Design** - _layout intuitivo e clean_
 <p>O aplicativo segue os padrões do MaterialDesign para uma melhor experiência do usuário em sua utilização.</p>

**Koin** _injeção de dependência_
 <p>Escolhida por sua simples implementação comparada ao Dagger (E recentemente com o Koin 2.0, o desempenho não é muito diferente).</p>

**Motion Laoyut** _animação para a collapsing toolbar_
  <p>Escolhido como o substituto da collapsing toolbar para exibir a imagem do filme. Ao arrastar para cima, essa imagem é escondida. </p>

**Fading Layout**
   <p>Para dar uma transparência na parte de baixo da imagem do filme, foi utilizado a biblioteca FadingLayout que disponibiliza um Layout com diversas configurações interessantes.</p>

   **Mockk para testes**
   <p>Por sua fácil implementação e permitir o teste do ViewModel com as Coroutines, essa foi a biblioteca utilizada.</p>

## O que eu gostaria de ter feito
 
 * _criado testes de ui;_
 
 * _implementado a tela inicial da aplicação.
