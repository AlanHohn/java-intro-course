package org.anvard.introtojava.dynamic.asm;

import static org.objectweb.asm.Opcodes.*;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import dynamic.Calculator;

public class DynamicCalculatorSample {

	public static void main(String[] args) throws Exception {
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		
		cw.visit(V1_7,								// Java 1.7 
				ACC_PUBLIC, 						// public class
				"dynamic/DynamicCalculatorImpl", 	// package and name
				null, 								// signature (null means not generic)
				"java/lang/Object",					// superclass
				new String[]{ "dynamic/Calculator" }); // interfaces
		
        /* Build constructor */
        MethodVisitor con = cw.visitMethod(
        		ACC_PUBLIC, 						// public method
        		"<init>", 							// method name 
        		"()V", 								// descriptor
        		null, 								// signature (null means not generic)
        		null);								// exceptions (array of strings)
        
        con.visitCode();
        con.visitVarInsn(ALOAD, 0);					// Load "this" onto the stack
        
        con.visitMethodInsn(INVOKESPECIAL, 			// Invoke an instance method (non-virtual)
        		"java/lang/Object", 				// Class on which the method is defined
        		"<init>", 							// Name of the method
        		"()V", 								// Descriptor
        		false);								// Is this class an interface?
        
        con.visitInsn(RETURN);						// End the constructor method
        con.visitMaxs(1, 1);						// Specify max stack and local vars
        
        /* Build 'add' method */
        MethodVisitor mv = cw.visitMethod(
        		ACC_PUBLIC, 						// public method
        		"add", 								// name
        		"(II)I", 							// descriptor
        		null, 								// signature (null means not generic)
        		null);								// exceptions (array of strings)
        
        mv.visitCode();
        mv.visitVarInsn(ILOAD, 1);					// Load int value onto stack
        mv.visitVarInsn(ILOAD, 2);					// Load int value onto stack
        mv.visitInsn(IADD);							// Integer add from stack and push to stack
        mv.visitInsn(IRETURN);						// Return integer from top of stack
        mv.visitMaxs(2, 3);							// Specify max stack and local vars
        
        cw.visitEnd();								// Finish the class definition
		
        DynamicClassLoader loader = new DynamicClassLoader();
        Class<?> clazz = loader.defineClass("dynamic.DynamicCalculatorImpl", cw.toByteArray());
        System.out.println(clazz.getName());
        Calculator calc = (Calculator)clazz.newInstance();
        System.out.println("2 + 2 = " + calc.add(2, 2));
	}
}
