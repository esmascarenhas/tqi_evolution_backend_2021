# tqi_evolution_backend_2021
<h1>
Projeto avaliação TQI Evolution 2022
</h1>

<p>Sugestão para o back-end de um: <strong>sistema de análise de crédito</strong>.
Projeto que tive o prazer em desenvolver para avaliação do tqi_evolution_backend_2021 da  <strong> <a href="https://www.tqi.com.br/"> TQI  </a></strong> 🧡💛.




<h2>
 🧮 Framework utilizados
</h2>

- [x] Java JDK 11
- [x] IDE IntelliJ
- [x] Documentação e consumo no Swagger 
- [x] Banco de Dados MySQL
- [x] Spring initializr 

<ul>
<h2> ✨ Desafio</h2>

🔸 <strong> Visão geral:  Sistema de Análise de Crédito para uma Empresa de Emprestimos </strong>

🔸 <strong> Cliente </strong><br>
	  Cadastro de clientes: nome, e-mail, CPF, RG, endereço completo, renda e senha.<br>
	  Consulta do Cadastro dos Clientes<br>
	  Demais metodos de alteração e exclusão do cadastro<br>
	 
🔸 <strong> Emprestimo </strong><br>
	  Solicitação de empréstimo: valor do empréstimo, data da primeira parcela e quantidade de parcelas.<br>
	  Acompanhamento das solicitações de empréstimo:  retornar no mínimo o código do empréstimo, o valor e a quantidade de parcelas.<br>
	  Detalhes de um dos empréstimos do cliente retornar: código do empréstimo, valor, quantidade de parcelas, data da primeira parcela, e-mail do cliente e renda do cliente.<br>
	 
🔸 <strong> Login </strong><br>
	  Autenticação será realizada por e-mail e senha.<br>
	  Classe e repositorio de Usuários <br>
	  Controlador para autenticação<br>
</ul>
	  
	  
<ul>
	<h2> 👣 Solução</h2>
	  
	  
	  
	

	  
	  
   <p><strong>Na Homepage do Swagger será apresentado quatro controladores:<br>
	
	🔻 Controle de Auteenticação - realiza geração de token para autenticação na API;<br>
	🔻 Cliente Controller - Contrala os serviços de cadastro, consulta, alteração e exclusão do Banco de dados;<br>
	🔻 Emprestimo Controller - Realiza solicitação de emprestimo, acompanhamento, detalhe, alteração e exclusão;<br>
	🔻 Status Aplicação - Testa se aplicação está funcionando.
	
    

	  
	  
	  
![Demo Animation](https://github.com/esmascarenhas/desafiotqi/blob/assets/swagger1.png?raw=true)
	 
	
🔸 <strong> Security </strong><br>
	  Utilização do JWT para geração e validação de token para autenticação.<br>
	  Spring Security para solução de segurança<br>
	  Autenticação Inmemory e via Banco de dados<br>
	  
   <p><strong>No Controlador de acesso:<br>

	
	🔻 Realiza o metodo post, utilizando o email e a senha do usuário;<br>
	🔻 Cadastrado, no Banco, dois usuários para teste:<br> 
	       Perfil ADMIN: email (admin@email.com) senha (654321)<br>
	       Perfil User: email (usuario@email.com) senha (123456)<br>
	🔻 Executando o metodo post, recebe o token e cadastra no autorizador (Cadeaddo no canto superior direito);<br>
	🔻 Pronto! Você está autorizado para utilizar os serviços que possui autenticação liberada(conforme consta na descrição de cada metodo.<br>
	


![Demo Animation](https://github.com/esmascarenhas/desafiotqi/blob/assets/Imagem1.png?raw=true)


🔸 <strong> Controlador de cliente - Composto dos seguintes metodos: </strong>
<br>
	   🔹 Método GET (sem parametro) - Consulta a base de clientes na Empresa;<br>
	   🔹 Método GET (com id do cliente) - Consulta o cadastro de um cliente na Empresa;<br>
	   🔹 Método Post (sem parametro) - Realiza o cadastro de clientes na Empresa;<br>
	   🔹 Método Put (com id do cliente) - Altera o cadastro de clientes na Empresa;<br>
	   🔹 Método Delete (com id do cliente) - Exclui um cliente do cadastro na Empresa;<br>
<br>
	

	   

![Demo Animation](https://github.com/esmascarenhas/desafiotqi/blob/assets/swagger3.png?raw=true)


	   
🔸 <strong> Controlador de Emprestimo - Composto dos seguintes metodos: </strong>
<br>
	   🔹 Método GET (sem parametro) - Consulta os Emprestimos dos clientes;<br>
	   🔹 Método GET (com id do cliente) - Acompanhamento dos Emprestimos de um cliente;<br>
	   🔹 Método Post (sem parametro) - Realiza o cadastro de Emprestimo para o cliente;<br>
	   🔹 Método Put (com id do cliente) - Altera informações do Emprestimo do cliente;<br>
	   🔹 Método Delete (com id do cliente) - Exclui um emprestimo do sistema;<br>
<br>


![Demo Animation](https://github.com/esmascarenhas/desafiotqi/blob/assets/Imagem2.png?raw=true)


	   
</ul>
🔸 <strong> Testes </strong><br>
	  Utilização do JUnit, REST-Assured e mockmvc para testes unitários e integração.<br>
  	  Cobertura de teste das classe Controller<br>
  	  Cobertura de testes das classes de serviço e repository<br>
	
	
<h2> 🤝 Fontes de Estudo e contribuições </h2>

Bootcamp da [DIO](https://digitalinnovation.one/).<br>
Desenvolvimento de API com a [AlgaWorks](https://www.youtube.com/c/AlgaWorksCursosOnline).<br>
Token com JWT e Spring Security [Expertos Tech](https://www.youtube.com/results?search_query=expertos+tech).<br>
Spring Boot e Spring Securit com [DevDojo](https://www.youtube.com/c/DevDojoBrasil).<br>
Testes Unitários e integração com [Julio de Lima](https://www.youtube.com/c/JuliodeLimas).<br>






------------

Disponibilizado por [Emerson](https://www.linkedin.com/in/emerson-mascarenhas-86b8462b).
