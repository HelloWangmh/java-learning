package wang.mh.tool;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;

import java.util.List;

public class WordSegment {

    public static void main(String[] args) {

        List<Word> list = WordSegmenter.seg("成田國際機場(NRT)");
        System.out.println(list);
        System.out.println( WordSegmenter.seg("北京上海石家庄酒店"));
        System.out.println( WordSegmenter.segWithStopWords("北京上海石家庄酒店"));

    }
}
