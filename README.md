## Erros Identificados

1. **Driver MySQL incorreto**
    - Linha: `Class.forName("com.mysql.Driver.Manager").newInstance();`
    - Correção: Substituir por `Class.forName("com.mysql.cj.jdbc.Driver");`.

2. **URL de conexão inválida**
    - Linha: `String url = "jbdc:mysql://127.0.0.1/test?user=lopes&password=123";`
    - Correção: Substituir `jbdc` por `jdbc`.

3. **Variável de conexão nunca inicializada**
    - O método `conectarBD` não inicializa `conn`. Isso resulta em uma conexão nula.

4. **Vulnerabilidade de SQL Injection**
    - Método: `verificarUsuario`
    - Correção: Utilizar *Prepared Statements* em vez de concatenar strings diretamente.

5. **Falta de fechamento de recursos**
    - Problema: Conexões, `Statement` e `ResultSet` não são fechados, causando vazamento de recursos.
    - Correção: Utilizar `try-with-resources`.

6. **Blocos `catch` vazios**
    - Problema: Exceções não são tratadas nem registradas, dificultando a depuração.
    - Correção: Adicionar mensagens de log ou exibir o *stack trace*.

7. **Ausência de validação de entrada**
    - Problema: Os valores de `login` e `senha` não são validados.
    - Correção: Adicionar validação antes de usá-los na consulta.
