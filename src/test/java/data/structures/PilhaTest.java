package data.structures;

import data.structures.implementations.PilhaImpl;
import data.structures.interfaces.Pilha;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PilhaTest {

    @Test
    void initializationOk() {
        //given
        int size = random(10);

        //when
        Pilha<Integer> pilha = new PilhaImpl<>(size);

        //then
        assertThat(pilha.size()).isEqualTo(size);
        assertThat(pilha.isEmpty()).isTrue();
        assertThat(pilha.top()).isNull();
    }

    @Test
    void initializationZeroSizeNotAllowed() {
        //given when then
        assertThatThrownBy(() -> new PilhaImpl<>(0))
                .isInstanceOf(RuntimeException.class) ;
    }

    @Test
    void initializationNegativeSizeNotAllowed() {
        //given
        final int size = -random(10);

        //when then
        assertThatThrownBy(() -> new PilhaImpl<>(size))
                .isInstanceOf(RuntimeException.class) ;
    }

    @ParameterizedTest
    @MethodSource("pushArguments")
    void pushOK(int size, int [] elements, int expectedTop, boolean expectedFull) {
        //given
        Pilha<Integer> pilha = new PilhaImpl<>(size);

        //when
        for(var element: elements) pilha.push(element);

        //then
        assertThat(pilha.top()).isEqualTo(expectedTop);
        assertThat(pilha.isFull()).isEqualTo(expectedFull);
        assertThat(pilha.isEmpty()).isFalse();
    }

    static Stream<Arguments> pushArguments() {
        int elementOne = random();
        int elementTwo = random();

        return Stream.of(
                arguments(2, new int[] {elementOne, elementTwo}, elementTwo, true),
                arguments(3, new int[] {elementOne, elementTwo}, elementTwo, false),
                arguments(1, new int[] {elementOne}, elementOne, true),
                arguments(2, new int[] {elementOne}, elementOne, false)
        );
    }

    @Test
    void pushFailedFullStack() {
        //given
        int elementOne = random();
        int elementTwo = random();
        Pilha<Integer> pilha = new PilhaImpl<>(1);
        pilha.push(elementOne);

        //when then
        assertThatThrownBy(() -> pilha.push(elementTwo))
                .isInstanceOf(RuntimeException.class) ;
    }

    @ParameterizedTest
    @MethodSource("popArguments")
    void popOk(Pilha<Integer> pilhaToBeUsed, int expectedElement, boolean expectedEmpty) {
        //given when
        var actualElement = pilhaToBeUsed.pop();

        //then
        assertThat(actualElement).isEqualTo(expectedElement);
        assertThat(pilhaToBeUsed.isEmpty()).isEqualTo(expectedEmpty);
        assertThat(pilhaToBeUsed.isFull()).isFalse();
    }
    static Stream<Arguments> popArguments() {
        int elementOne = random();
        int elementTwo = random();

        Pilha<Integer> pilhaOneElementFull = new PilhaImpl<>(1);
        pilhaOneElementFull.push(elementOne);

        Pilha<Integer> pilhaTwoElementsFull = new PilhaImpl<>(2);
        pilhaTwoElementsFull.push(elementOne);
        pilhaTwoElementsFull.push(elementTwo);

        Pilha<Integer> pilhaTwoElementsNotFull = new PilhaImpl<>(2);
        pilhaTwoElementsNotFull.push(elementOne);

        return Stream.of(
                arguments(pilhaOneElementFull, elementOne, true),
                arguments(pilhaTwoElementsFull, elementTwo, false),
                arguments(pilhaTwoElementsNotFull, elementOne, true)
        );
    }

    @Test
    void popFailedEmptyStack() {
        //given
        int size = random(10);
        Pilha<Integer> pilha = new PilhaImpl<>(size);

        //when then
        assertThatThrownBy(pilha::pop)
                .isInstanceOf(RuntimeException.class) ;
    }

    @Test
    void verifyStack() {
        //given
        int elementOne = random();
        int elementTwo = random();
        int elementThree = random();

        //when then
        Pilha<Integer> pilha = new PilhaImpl<>(3);
        assertThat(pilha.isEmpty()).isTrue();
        assertThat(pilha.isFull()).isFalse();
        assertThat(pilha.top()).isNull();

        pilha.push(elementOne);
        assertThat(pilha.isEmpty()).isFalse();
        assertThat(pilha.isFull()).isFalse();
        assertThat(pilha.top()).isEqualTo(elementOne);

        pilha.push(elementTwo);
        assertThat(pilha.isEmpty()).isFalse();
        assertThat(pilha.isFull()).isFalse();
        assertThat(pilha.top()).isEqualTo(elementTwo);

        pilha.push(elementThree);
        assertThat(pilha.isEmpty()).isFalse();
        assertThat(pilha.isFull()).isTrue();
        assertThat(pilha.top()).isEqualTo(elementThree);

        assertThat(pilha.pop()).isEqualTo(elementThree);
        assertThat(pilha.isEmpty()).isFalse();
        assertThat(pilha.isFull()).isFalse();

        assertThat(pilha.pop()).isEqualTo(elementTwo);
        assertThat(pilha.isEmpty()).isFalse();
        assertThat(pilha.isFull()).isFalse();

        assertThat(pilha.pop()).isEqualTo(elementOne);
        assertThat(pilha.isEmpty()).isTrue();
        assertThat(pilha.isFull()).isFalse();
    }

    private static int random() {
        return new Random().nextInt();
    }

    private static int random(int limit) {
        var number = 0;
        while (number == 0) {
            number = new Random().nextInt(limit+1);
        }
        return number;
    }
}