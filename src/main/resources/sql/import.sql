INSERT INTO TESTE (MOVIEYEAR, TITLE, STUDIOS, PRODUCERS, WINNER) SELECT * FROM CSVREAD('classpath:movielist.csv', null, 'fieldSeparator=;');