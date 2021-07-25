package org.canzuo;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.canzuo.mapper.StringMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Scanner;

@RunWith(JUnitParamsRunner.class)
@Epic("ShowHand Test")
@Feature("集成测试:桌子上两手牌的大小判定")
public class CroupierFinalJudgeTest {
    @Test
    @Parameters({"5C 5D 2C 2D AS,2S 2H 3C 3D AD,Black wins.",
            "AH KH QH JH 3C,2S 2H 2C 3H TS,White wins.",
            "AH KH JH QH TH,AS KS JS TS QS,Tie.",
            "AS KS AH AD KD,JS KS QS 9S TS,White wins."})
    public void testJudgement(String blackHands, String whiteHands, String result) {
        Readable readable = new ShowHandInDeck(blackHands, whiteHands);
        Scanner output = new Scanner(readable);
        while(output.hasNextLine()) {
            Assert.assertEquals(output.nextLine(), result);
        }
    }

    @Test
    @FileParameters(value = "src/test/resources/PokerHand.csv", mapper = StringMapper.class)
    public void testJudgementFromFile(String cardsInDeck, String result) {
        Readable readable = new ShowHandInDeck(cardsInDeck);
        Scanner output = new Scanner(readable);
        while(output.hasNextLine()) {
            Assert.assertEquals(output.nextLine(), result);
        }
    }
}
