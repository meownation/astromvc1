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




INSERT INTO paragraph(topic,text) values ('Ljubav', 'Ne razumete partnerov stav o zajedničkom problemu, imate utisak da se nalazite na suprotnim stranama.');
INSERT INTO paragraph(topic,text) values ('Ljubav', 'Izbegavajte napete situacije u susretu sa voljenom osobom, važno je da među Vama postoje dobre namere.');
INSERT INTO paragraph(topic,text) values ('Ljubav', 'Nema potrebe da strahujete od istine ili da potiskujete svoje želje u društvu bliske osobe.');
INSERT INTO paragraph(topic,text) values ('Ljubav', 'Mogući su različiti konflikti ili nerazumevanje, voljena osoba kao da nema previše razumevanja za Vaše ćudljivo ponašanje.');
INSERT INTO paragraph(topic,text) values ('Ljubav', 'Očekuje Vas prijatno iznenađenje u susretu sa voljenom osobom. Budite dovoljno maštoviti i inspirativni u svakom pogledu.');
INSERT INTO paragraph(topic,text) values ('Ljubav', 'Stvari ponekad izgledaju potpuno drugačije od prvog utiska koji imate. Postupite po svojoj savesti u susretu sa voljenom osobom.');
INSERT INTO paragraph(topic,text) values ('Ljubav', 'Nemojte davati velika i ishitrena obećanja voljenoj osobi. Moguće je da ćete se više puta predomisliti.');
INSERT INTO paragraph(topic,text) values ('Ljubav', 'Često menjate svoje raspoloženje ili odluke. Veću važnost ima ono što prećutkujete, nego stvari koje saopštavate partneru.');
INSERT INTO paragraph(topic,text) values ('Ljubav', 'Nema potrebe da se sukobljavate sa bliskom osobom. Emotivne nesuglasice nisu značajne za nove događaje.');
INSERT INTO paragraph(topic,text) values ('Ljubav', 'Priuštite svojoj porodici ili voljenoj osobi dobru zabavu. Uz iskrene namere usrećite osobu do koje Vam je stalo.');
INSERT INTO paragraph(topic,text) values ('Ljubav', 'Za sada vešto korisite svoje sposobnosti na različitim stranama. Partner ne može da odoli Vašem šarmu.');
INSERT INTO paragraph(topic,text) values ('Ljubav', 'Delujete uznemireno, teško pronalazite svoju »mirnu luku« i emotivni predah. Nedostaje Vam poverenje u jednu blisku osobu.');

INSERT INTO paragraph(topic,text) values ('Posao', 'Izbegavajte sumnjive okolnosti i pažljivije analizirajte svoje saradnike. Ponekad postoje »skriveni detalji« koji se teže uočavaju.');
INSERT INTO paragraph(topic,text) values ('Posao', 'Ukoliko Vam je stalo da testirate svoje intelektualne ili praktične sposobnosti, potrebno je da imate precizne ciljeve.');
INSERT INTO paragraph(topic,text) values ('Posao', 'Sve ima svoju granicu ili cenu, tako i radna terapija ima pozitivne efekte samo pod određenim uslovima');
INSERT INTO paragraph(topic,text) values ('Posao', 'Očekujete pozitivan odgovor ili povratnu informaciju o poslovnoj saradnji, međutim sve je obavijeno velom neizvesnosti.');

INSERT INTO paragraph(topic,text) values ('Zdravlje', 'U večernjim satima prijaće Vam izlazak, šetnja ili relaksacija.');
INSERT INTO paragraph(topic,text) values ('Zdravlje', 'Potrudite se da pravilno usmeravate svoju pozitivnu energiju.');
INSERT INTO paragraph(topic,text) values ('Zdravlje', 'Olakšajte svoju savest, prijaće Vam relaksacija.');
INSERT INTO paragraph(topic,text) values ('Zdravlje', 'Obratite pažnju na zdraviji način ishrane, izbegavajte neke »loše« navike.');

INSERT INTO dailyHoroscope(prediction_date, astrosign, topic1, topic2, topic3) values ('2022-05-30', 'LEO', 7, 15, 20);