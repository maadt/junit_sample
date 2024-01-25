package com.example;

// staticインポート
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

/*
JUnitテスト手順
1, 「src/test/java」配下、テスト対象となるフォルダと同等のディレクトリに「クラス名 + Test」の「JUnit テスト・ケース」を作成。
2, テストメソッドを作成したら、実行 → JUnitテスト
３, ウィンドウに結果が表示される
 */

class CalculatorTest {
	
	// 未実装パターン
    @Test
    // @Test：テストをするメソッド全てに付ける
    void test() {
    // void；必ずvoid型
        fail("まだ実装されていません");
        // staticインポートによりクラス名は記入不要
        // fail()：無条件でテストを失敗扱いにする
    }
    
    // 成功パターン
    @Test
    void multiplyで3と4の乗算結果を取得する() {
    // テストメソッド名はどのようなテストが行われているかを明確できるように日本語で具体的に名づける
        // Calculatorクラスのインスタンスを生成する
        Calculator sut = new Calculator();
        //sut：System under testの略。テスト対象の意。
        int expected = 12;
        // 想定する計算結果の用意
        int actual = sut.multiply(3, 4);
        // 3 x 4の結果を`multiply()`に計算させる
        assertThat(actual).isEqualTo(expected);
        // 計算結果が12となるか検証する
    }
    
    // 失敗パターン
    @Test
    void multiplyで5と6の乗算結果を取得する() {
        Calculator sut = new Calculator();
        int expected = 12;
        int actual = sut.multiply(5, 6);
        assertThat(actual).isEqualTo(expected);
        /*
         障害トレース
         expected: 12 期待値
         but was: 30 結果
         */
    }
    
    // 失敗パターン２（修正済み）
    @Test
    void devideで3と2の除算結果を取得する() {
        Calculator sut = new Calculator();
        double expected = 1.5;
        double actual = sut.devide(3, 2);
        assertThat(actual).isEqualTo(expected);
        /*
        障害トレース
        expected: 1.5 期待値
        but was: 1.0 結果
        */
    }
}