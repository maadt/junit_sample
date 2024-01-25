package com.example;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested; // Nestedクラスをインポート
import org.junit.jupiter.api.Test;

// 構造化：複数のテストクラスを1つのテストクラスにまとめて定義し、テストケースが増えた場合に効果的にテストを行うことが出来る

/*
 テストの構造化のポイント
 ・org.junit.jupiter.api.Nestedをインポートする
 ・テストクラスの中にネストしたテストクラスを定義し、@Nestedを付与する
 ・ネストしたクラス名はわかりやすい名前にし、クラスごとにフィールドやメソッドを定義する
 */

public class ItemTest {
    // ネストしたクラスごとに@Nestedを付与
    @Nested
    // クラスごとの目的がわかるように名前を付ける
    public class 名前がnullの場合 {
        private Item sut;
        @BeforeEach
        // Itemオブジェクトのフィールドnameはnullのままにします
        public void setUp() {
            this.sut = new Item();
        }

        @Test
        public void nameがnullを返す() {
            assertThat(sut.getName()).isNull();
        }
    }

    @Nested
    public class 名前が商品Aの場合 {
        private Item sut;
        @BeforeEach
        // Itemオブジェクトのフィールドnameに値をセットします
        public void setUp() {
            this.sut = new Item();
            sut.setName("商品A");
        }

        @Test
        public void nameが商品Aである() {
            String expected = "商品A";
            assertThat(sut.getName()).isEqualTo(expected);
        }
    }
}

