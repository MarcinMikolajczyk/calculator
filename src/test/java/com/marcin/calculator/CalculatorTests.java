package com.marcin.calculator;

import com.marcin.calculator.data.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTests {


    @ParameterizedTest
    @MethodSource("dataWithScriptsToExecute")
    void executeInstructions_With_Correct_File(String url, int correct_result) {
        //given
        File instructionsFile = new File(url);
        List<Pair<Calculator.Operation, Integer>> instructionsList =
                Calculator.getInstructionsFromFile(instructionsFile);
        //when
        int result = Calculator.executeInstructions(instructionsList);
        //then
        assertEquals(correct_result, result);
    }

    @Test
    void executeInstructions_With_Empty_Instructions_List() {
        //given
        List<Pair<Calculator.Operation, Integer>> instructionsList = new ArrayList<>();
        //when
        //then
        assertThrows(IllegalStateException.class, () -> Calculator.executeInstructions(instructionsList));
    }

    @Test
    void getInstructionsFromFile_Checking_Size_Of_List() {
        //given
        File instructionsFile = new File("src/test/resources/script_one.txt");
        //when
        List<Pair<Calculator.Operation, Integer>> instructionsList =
                Calculator.getInstructionsFromFile(instructionsFile);
        //then
        assertEquals(3, instructionsList.size());
    }

    @Test
    void mainNo_File_Name_inputted_As_A_Parameter(){
        //given
        String[] args = {};
        //when
        //then
        assertThrows(IllegalStateException.class, () -> Calculator.main(args));
    }

    @Test
    void mainNo_File_Founded(){
        //given
        String[] args = {"src/test/resources/"};
        //when
        //then
        assertThrows(IllegalStateException.class, () -> Calculator.main(args));
    }


    private static Stream<Arguments> dataWithScriptsToExecute(){
        return Stream.of(
                Arguments.of("src/test/resources/script_one.txt", 36),
                Arguments.of("src/test/resources/script_two.txt", 32),
                Arguments.of("src/test/resources/script_three.txt", 1)
        );
    }

}
