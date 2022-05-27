CREATE TABLE IF NOT EXISTS daily (
id BIGSERIAL PRIMARY KEY,
prediction_date DATE,
astrosign VARCHAR(15),
payload VARCHAR(100)
);

INSERT INTO daily (prediction_date, astrosign, payload) values ('2022-05-27', 'LEO', 'Leo strong from DB');
INSERT INTO daily (prediction_date, astrosign, payload) values ('2022-05-27', 'TAURUS', 'TAURUS strong from DB');