# Compiladores-Desafio de Validação
Repositório para resolver o desafio de validação na matéria de Compiladores

Desafio: Validar linhas de código com ()[]{} válidos

1 Ponto extra 1B N2

Projeto individual

Projeto em Java

Ler um arquivo texto e marcar cada linha com OK ou Inválido:

{[]} - OK

([)] - Inválido

[{()()}[]] - OK

{}()[()]] - Inválido

)[{}]()( - Inválido

(()[)] - Inválido

etc...

Criar um programa java que recebe um arquivo texto com código via linha de comando, exemplo:

java prog.txt
-> gera prog-check.txt

Foi utilizado IntelliJ IDEA Community Edition Version 2019.3.4
