# :computer: Controle de Receitas e Despesas

## O que é o projeto?
O "Controle de Receitas e Despesas" é uma API que eu criei para ajudar a gerenciar as finanças pessoais de um jeito fácil e prático. A ideia é que você consiga registrar suas receitas e despesas de forma organizada. A API tem rotas que permitem fazer todas as operações de CRUD (criação, leitura, atualização e exclusão) para Clientes, Endereços e Contas.

## :question: Por que uma API?
Fiz a escolha de desenvolver uma API porque isso traz várias vantagens:

- **Desacoplamento**: Front-end e back-end podem ser trabalhados em paralelo.
- **Escalabilidade**: Fica mais fácil adaptar e escalar o projeto com o tempo.
- **Integração**: Outras aplicações podem se conectar e interagir com a API sem complicações.

## :wrench: Tecnologias que usei
Decidi me desafiar com **Java** e **Spring Boot**, que são ferramentas que venho estudando e aprimorando. Para armazenar os dados, usei o **PostgreSQL**, um banco de dados super legal e confiável.

## :white_check_mark: Testes Unitários
Criar testes foi uma parte importante do processo, e isso me ajudou a consolidar conhecimentos em:

- Garantir que o código funciona direitinho.
- Identificar e corrigir bugs de forma mais rápida.

## :small_orange_diamond: Exemplo de JSON para Criar Cliente
Para testar a criação de um cliente, usei o seguinte JSON:

```json
{
  "nome": "João da Silva",
  "telefone": "123456789",
  "dataCriacao": "2023-09-19",
  "ativo": true,
  "endereco": {
    "cidade": "São Paulo",
    "bairro": "Centro",
    "logradouro": "Rua das Flores",
    "numero": "123",
    "complemento": "Apto 45",
    "cep": "01234-567"
  },
  "contas": [
    {
      "valorConta": 1500.00,
      "valorFatura": 200.00
    }
  ],
  "movimentos": [
    {
      "tipo_movimento": "E",
      "valor": 1000,
      "dataMovimentacao": "2023-09-19"
    },
    {
      "tipo_movimento": "S",
      "valor": 500,
      "dataMovimentacao": "2023-09-20"
    }
  ]
}
```
## :small_orange_diamond: Procedures e Relatórios
Criei uma stored procedure que calcula e mostra os valores das movimentações, permitindo gerar relatórios de maneira eficiente. Trabalhar com procedures foi super interessante, pois ajudou a otimizar as consultas e melhorar o desempenho geral da API.

### Exemplo de Procedure
Aqui está a procedure que eu criei para somar todos os valores da tabela `movimentacao`:

```sql
CREATE OR REPLACE FUNCTION somar_valores_movimentacao()
RETURNS NUMERIC AS $$
DECLARE
    total NUMERIC;
BEGIN
    SELECT SUM(valor) INTO total FROM movimentacao;

    RETURN COALESCE(total, 0); -- Retorna 0 se não houver movimentações
END;
$$ LANGUAGE plpgsql;
```

## :sparkles: Melhores Práticas
Durante o desenvolvimento, adotei algumas melhores práticas que foram super úteis, como:

- **Organização do Código**: Separei tudo em pacotes de Repository, Service, Controller e Model, deixando o projeto mais limpo.
- **Tratamento de Erros**: Implementar um bom manejo de exceções para garantir respostas claras na API.

## :pushpin: Design Patterns
Usei alguns design patterns que tornaram o projeto mais eficiente, como:

- **Repository Pattern**: Para facilitar o acesso aos dados e fazer testes mais tranquilos.
- **Service Layer Pattern**: Para separar a lógica de negócios e deixar tudo mais organizado.

##  :rocket: Considerações Finais
Esse projeto foi uma ótima oportunidade para colocar em prática o que eu aprendi até agora. Foi legal explorar novas tecnologias e resolver problemas de forma criativa.
