CREATE TABLE IF NOT EXISTS paragraph(
id BIGSERIAL PRIMARY KEY,
topic VARCHAR(30),
text VARCHAR(300)
);

CREATE TABLE IF NOT EXISTS dailyHoroscope (
id BIGSERIAL PRIMARY KEY,
prediction_date DATE,
astrosign VARCHAR(15),
topic1 BIGINT,
topic2 BIGINT,
topic3 BIGINT,
FOREIGN KEY(topic1) REFERENCES paragraph(id),
FOREIGN KEY(topic2) REFERENCES paragraph(id),
FOREIGN KEY(topic3) REFERENCES paragraph(id)
);
