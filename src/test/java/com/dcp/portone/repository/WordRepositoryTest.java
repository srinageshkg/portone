package com.dcp.portone.repository;

/*
@SpringBootTest
class WordRepositoryTest {
    @Autowired
    private WordsRepository wordsRepository;

    @Test
    public void saveWordsFromFile() {
        String line = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/words.csv"));

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Word word = new Word();

                word.setWordNo(Integer.valueOf(data[0]));
                word.setWord(data[1]);
                word.setPartOfSpeech(data[2]);
                word.setLetterCount(Integer.valueOf(data[3]));

                wordsRepository.save(word);
            }

        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }
}*/
