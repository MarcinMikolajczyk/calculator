package com.marcin.calculator;

import com.marcin.calculator.data.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Marcin Mikołajczyk
 * @version 1.0
 */
public class Calculator {

    public static void main(String[] args) throws FileNotFoundException {

        if (args.length != 1)
            throw new IllegalStateException("There is no path with calculations to execute");

        File calculationsFile = new File(args[0]);

        if (!calculationsFile.exists())
            throw new FileNotFoundException(String.format("File %s don't exist", calculationsFile.getPath()));

        List<Pair<Operation, Integer>> instructions = getInstructionsFromFile(calculationsFile);
        int result = executeInstructions(instructions);

        System.out.println(result);
    }

    /**
     * Executing given instructions
     *
     * @param instructions list witch instructions
     * @return result of calculations
     */
    static
    int executeInstructions(List<Pair<Operation, Integer>> instructions) {
        if (instructions.isEmpty())
            throw new IllegalStateException("List witch instructions cannot be empty");
        if (instructions.get(0).getLeft() != Operation.apply)
            throw new IllegalStateException("First instruction must be apply");

        int result = instructions.get(0).getRight();

        for (Pair<Operation, Integer> instruction : instructions) {
            switch (instruction.getLeft()) {
                case add:
                    result += instruction.getRight();
                    break;
                case multiply:
                    result *= instruction.getRight();
                    break;
                case subtract:
                    result -= instruction.getRight();
                    break;
                case divide:
                    result /= instruction.getRight();
                    break;
            }
        }
        return result;
    }

    /**
     * Converts file into a list of instructions
     *
     * @param calculationsFile file with instructions to executed
     * @return list witch instructions
     */
    static
    List<Pair<Operation, Integer>> getInstructionsFromFile(File calculationsFile) {
        List<Pair<Operation, Integer>> instructions = new ArrayList<>();

        try (Scanner scanner = new Scanner(calculationsFile)) {
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split("\\u0020");

                Operation operation = Operation.valueOf(line[0]);
                Integer arg = Integer.parseInt(line[1]);

                Pair<Operation, Integer> instruction = new Pair<>(operation, arg);

                if (operation.equals(Operation.apply))
                    instructions.add(0, instruction);
                else
                    instructions.add(instruction);
            }
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return instructions;
    }

    enum Operation { add, divide, multiply, subtract, apply }
}


