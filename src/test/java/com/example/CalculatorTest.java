package com.example;

import static org.assertj.core.api.Assertions.*;

/*
  クラスインスタンスの初期化などを必要なテストクラス共通の処理を分離させておきたいときに作成します。
  慣習としてメソッド名はsetUpとし、publicなメソッドで返り値はvoid、引数は無しとします。
*/
import org.junit.jupiter.api.AfterEach;
// staticインポート。クラス名は記入不要となる
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest; //パラメータ化で利用
import org.junit.jupiter.params.provider.CsvSource; //複数同時のパラメータ化で利用
import org.junit.jupiter.params.provider.ValueSource; //パラメータ化で利用

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
        // assertThat()：実測値を指定するためのメソッド
        // isEqualTo()：期待値の設定を行うために検証用メソッド
        /*
         その他検証用メソッド
         ・isEmpty()：空文字を検証する　成功例）assertThat("").isEmpty();
         ・isNull()：オブジェクトがNULLかを検証する　成功例）assertThat(null).isNull();
         ・isNotNull()：オブジェクトがNULL以外かを検証する　成功例）assertThat("").isNotNull();
         ・isBetween(int start, int end)：実測値が指定の範囲内か　成功例）assertThat(5).isBetween(4, 6);
         ・contains(Object ... values)：実測値に指定の文字が含まれるか　成功例）assertThat("abcde").contains("b", "d");
         ・contains(Object ... values)：配列やコレクションに値が含まれるか　成功例）assertThat({1,2,3}).contains(1,3);
         */
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
    // 修正内容：Calculatorクラスの除算メソッドの戻り値の型をdoubleに、処理内でキャスト
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
    
    
    
    // 事前処理と事後処理
    // テストの事前処理を作成
    private Calculator sut;
    // フィールドを定義
    @BeforeEach
    // 事前処理を定義
    public void setUp() {
        this.sut = new Calculator();
        // 共通で利用するインスタンスを先に作成
     }
    
     // テストの事後処理を作成
     @AfterEach
     public void tearDown() {
        this.sut = null;
        // インスタンスの解放を必ず行う
    }
     
    /*
     @BeforeTestClass：テスト全体の処理の最初に1回実行
     @AfterTestClass：テスト全体の処理の最後に1回実行
     ・基本的にはテストはテストメソッドごとに処理を完結させる方が好ましい
     ・クラス全体で1度実行させたい処理があるかを検討する
     */
     
     
     
     // 複数のアサーションの実行
     // 全てのアサーションに成功した場合のみユニットテストが成功となる
     @Test
     public void 複数の値比較() {
         assertThat(100).isEqualTo(100);
         assertThat(5).isBetween(4, 6);
     }
     
     
     
     // 例外処理
     @Test
     public void 整数を0除算する１() throws Exception {
         Integer actual = 5 / 0; // ここでArithmeticExceptionが発生
     }
     
     @Test
     public void 整数を0除算する２() throws Exception {
         assertThatThrownBy(()->{
         // assertThrownBy()：指定した例外が発生たかどうかをテストする
             Integer actual = 5 / 0;
         })
         .isInstanceOf(ArithmeticException.class)
         // isInstanceOf()：発生しうる例外を記述
         .hasMessage("/ by zero");
         // hasMessage()：例外が発生した時に返ってくるエラー文を記述
     }
     
     
     
     //パラメータ化
     @ParameterizedTest // パラメータ化を定義
     @ValueSource(ints = {1, 3, 5, -3, -9, Integer.MAX_VALUE})
     /* 
        @ValueSource：型を指定して配列をセット。複数設定することもできる
          その他パラメータ型
          @ValueSource( bytes = {1, 2, 127}) // byte型
          @ValueSource( chars = {'a', 'b', 'c'}) // char型
          @ValueSource( doubles = {1.1, 2.2}) // double型
          @ValueSource( floats = {1.1f, 2.2f}) // float型
          @ValueSource( ints = {1, 2, 2147483647}) // int型
          @ValueSource( longs = {100L, 200L, 9223372036854775807L}) // long型
          @ValueSource( shorts = {10, 50, 32767}) // short型
          @ValueSource( strings = {"a", "b", "c"} // String型
     */
     // ints = {}のようにするとパラメータはint型となります
     // Integer.MAX_VALUE：int型の最大値である2,147,483,647で奇数
     public void 複数のパラメータが奇数であるかを検証する(int param){
     // 引数は@ValueSourceにセットした値が先頭から渡される
         boolean actual = sut.isOdd(param);
         boolean expected = true;
         assertThat(actual).isEqualTo(expected);
     }
     
     
     
     // パラメータ化（複数同時）
     @ParameterizedTest
     @CsvSource({
     // @CsvSource：1度のメソッド実行の度に、配列にセットした複数のパラメータを渡す
         "6,3,2.0",
         "5,3,1.6666666666666667",
         "0,5,0.0"})
     public void 一度に複数のパラメータを扱う(int x, int y, double expected ) {
     // 1つ目のパラメータでテストする時、xには6, yには3, expectedには2.0がセットされる
         double actual = sut.devide(x, y);
         assertThat(actual).isEqualTo(expected);
     }
}