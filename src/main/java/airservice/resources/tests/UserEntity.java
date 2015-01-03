/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package airservice.resources.tests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
public class UserEntity {
    
    private String nullableInput;
    @NotNull(message = "firstName cannot be null")
    private String firstName;
    @NotNull(message = "lastName cannot be null")
    private String lastName;
    private String nullableOutput;

    public UserEntity() {
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNullableInput(String nullableInput) {
        this.nullableInput = nullableInput;
    }

    public void setNullableOutput(String nullableOutput) {
        this.nullableOutput = nullableOutput;
    }

    public String getNullableInput() {
        return nullableInput;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNullableOutput() {
        return nullableOutput;
    }
    
    
}
