
-- Dados iniciais para a tabela `aplicativo`
INSERT INTO aplicativo (codigo, nome, custo_mensal) VALUES (1, 'App de Música', 29.99);
INSERT INTO aplicativo (codigo, nome, custo_mensal) VALUES (2, 'App de Vídeo', 49.99);
INSERT INTO aplicativo (codigo, nome, custo_mensal) VALUES (3, 'App de Jogos', 19.99);
INSERT INTO aplicativo (codigo, nome, custo_mensal) VALUES (4, 'App de Leitura', 14.99);
INSERT INTO aplicativo (codigo, nome, custo_mensal) VALUES (5, 'App de Educação', 39.99);
INSERT INTO aplicativo (codigo, nome, custo_mensal) VALUES (6, 'App de Saúde', 24.99);

-- Dados iniciais para a tabela `cliente`
INSERT INTO cliente (codigo, nome, email) VALUES (1, 'João Silva', 'joao.silva@email.com');
INSERT INTO cliente (codigo, nome, email) VALUES (2, 'Maria Souza', 'maria.souza@email.com');
INSERT INTO cliente (codigo, nome, email) VALUES (3, 'Carlos Pereira', 'carlos.pereira@email.com');
INSERT INTO cliente (codigo, nome, email) VALUES (4, 'Ana Lima', 'ana.lima@email.com');
INSERT INTO cliente (codigo, nome, email) VALUES (5, 'Pedro Oliveira', 'pedro.oliveira@email.com');
INSERT INTO cliente (codigo, nome, email) VALUES (6, 'Fernanda Costa', 'fernanda.costa@email.com');
INSERT INTO cliente (codigo, nome, email) VALUES (7, 'Rafael Almeida', 'rafael.almeida@email.com');
INSERT INTO cliente (codigo, nome, email) VALUES (8, 'Juliana Mendes', 'juliana.mendes@email.com');
INSERT INTO cliente (codigo, nome, email) VALUES (9, 'Bruno Santos', 'bruno.santos@email.com');
INSERT INTO cliente (codigo, nome, email) VALUES (10, 'Patrícia Barbosa', 'patricia.barbosa@email.com');
INSERT INTO cliente (codigo, nome, email) VALUES (11, 'Lucas Martins', 'lucas.martins@email.com');
INSERT INTO cliente (codigo, nome, email) VALUES (12, 'Beatriz Oliveira', 'beatriz.oliveira@email.com');
INSERT INTO cliente (codigo, nome, email) VALUES (13, 'Renato Castro', 'renato.castro@email.com');

-- Dados iniciais para a tabela `assinatura`
INSERT INTO assinatura (aplicativo_id, cliente_id, inicio_vigencia, fim_vigencia) VALUES (1, 1, '2023-01-01', '2023-12-31');
INSERT INTO assinatura (aplicativo_id, cliente_id, inicio_vigencia, fim_vigencia) VALUES (2, 2, '2023-02-01', '2023-12-31');
INSERT INTO assinatura (aplicativo_id, cliente_id, inicio_vigencia, fim_vigencia) VALUES (3, 3, '2023-03-01', '2023-12-31');
INSERT INTO assinatura (aplicativo_id, cliente_id, inicio_vigencia, fim_vigencia) VALUES (4, 4, '2024-04-01', '2024-12-31');
INSERT INTO assinatura (aplicativo_id, cliente_id, inicio_vigencia, fim_vigencia) VALUES (5, 5, '2024-05-01', '2024-12-31');
INSERT INTO assinatura (aplicativo_id, cliente_id, inicio_vigencia, fim_vigencia) VALUES (6, 6, '2024-06-01', '2024-12-31');
INSERT INTO assinatura (aplicativo_id, cliente_id, inicio_vigencia, fim_vigencia) VALUES (5, 7, '2024-07-01', '2024-12-31');
INSERT INTO assinatura (aplicativo_id, cliente_id, inicio_vigencia, fim_vigencia) VALUES (6, 8, '2024-08-01', '2024-12-31');

-- Dados iniciais para a tabela `usuario`
INSERT INTO usuario (usuario, senha) VALUES ('admin', 'admin123');
