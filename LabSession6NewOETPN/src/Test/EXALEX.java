package Exp1;

import Components.*;
import DataObjects.DataFloat;
import DataObjects.DataSubPetriNet;
import DataOnly.SubPetri;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

import java.util.ArrayList;

public class Exp1 {
    public static void main(String[] args) {
        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Main Petri";
        pn.NetworkPort = 1080;

        // ----------------------- sub petri ------------------------------------
        PetriNet spn = new PetriNet();
        spn.PetriNetName = "Sub Petri";


        // ONLY IF WE NEED CONSTANT VALUES FOR GUARD CONDITIONS
//        DataFloat subConstantValue1 = new DataFloat();
//        subConstantValue1.SetName("subConstantValue1");
//        subConstantValue1.SetValue(0.1f);
//        spn.ConstantPlaceList.add(subConstantValue1);


        DataFloat p8 = new DataFloat();
        p8.SetName("p8");
        p8.SetValue(1.0f); // Adaugare valoare initiala
        spn.PlaceList.add(p8);

        DataFloat p9 = new DataFloat();
        p9.SetName("p9");
//        p9.SetValue(1.0f); // Adaugare valoare initiala
        spn.PlaceList.add(p9);

        DataFloat p10 = new DataFloat();
        p10.SetName("p10");
//        p9.SetValue(1.0f); // Adaugare valoare initiala
        spn.PlaceList.add(p10);

        DataFloat p11 = new DataFloat();
        p11.SetName("p11");
//        p9.SetValue(1.0f); // Adaugare valoare initiala
        spn.PlaceList.add(p11);

        // T6 ------------------------------------------------
        PetriTransition t6 = new PetriTransition(spn);
        t6.TransitionName = "t6";
        t6.InputPlaceName.add("p8");
        Condition T6Ct1 = new Condition(t6, "p8", TransitionCondition.NotNull); // Mai intai definesti conditia de Guard
        GuardMapping grdT6 = new GuardMapping(); // Definesti Guard-ul
        grdT6.condition = T6Ct1; // Adaugi la Guard conditia pe care ai facut-o trecut
        grdT6.Activations.add(new Activation(t6, "p8", TransitionOperation.Move, "p9")); // Aici definesti map-ul care se executa dupa Guard daca Guard == true

        t6.GuardMappingList.add(grdT6);
        t6.Delay = 0;
        spn.Transitions.add(t6); // Adding a transition to the Sub Petri Net

        // T7 ------------------------------------------------
        PetriTransition t7 = new PetriTransition(spn);
        t7.TransitionName = "t7";
        t7.InputPlaceName.add("p9");
        Condition T7Ct1 = new Condition(t7, "p9", TransitionCondition.NotNull); // Mai intai definesti conditia de Guard
        GuardMapping grdT7 = new GuardMapping(); // Definesti Guard-ul
        grdT7.condition = T7Ct1; // Adaugi la Guard conditia pe care ai facut-o trecut
        grdT7.Activations.add(new Activation(t7, "p9", TransitionOperation.Add, "p10")); // Aici definesti map-ul care se executa dupa Guard daca Guard == true
        grdT7.Activations.add(new Activation(t7, "p9", TransitionOperation.Add, "p11")); // Aici definesti map-ul care se executa dupa Guard daca Guard == true
        grdT7.Activations.add(new Activation(t7, "p11", TransitionOperation.Move, "p7")); // Aici definesti map-ul care se executa dupa Guard daca Guard == true
        grdT7.Activations.add(new Activation(t7, "p9", TransitionOperation.Sub, "p10")); // Aici definesti map-ul care se executa dupa Guard daca Guard == true
        t7.GuardMappingList.add(grdT7);
        t7.Delay = 0;
        spn.Transitions.add(t7); // Adding a transition to the Sub Petri Net

        // T8 - STOP PETRI NET ------------------------------------------------
        PetriTransition t8 = new PetriTransition(spn);
        GuardMapping grdT8 = new GuardMapping(); // Definesti Guard-ul
        grdT8.Activations.add(new Activation(t8, "", TransitionOperation.StopPetriNet, ""));
        t8.GuardMappingList.add(grdT8);
        t8.Delay = 0;
        spn.Transitions.add(t8);
        spn.Delay = 3000; // END OF SPN ------------------------------------------------------------------------


        // PN2 RAMANE



        //  ----------------------- MAIN petri ------------------------------------
        DataSubPetriNet subPetriNet = new DataSubPetriNet();
        subPetriNet.SetName("SubPetri");
        SubPetri sptr = new SubPetri(spn);
        subPetriNet.SetValue(sptr);

        // PLACES
        DataFloat p0 = new DataFloat();
        p0.SetName("p0");
        p0.SetValue(1.0f);
        pn.PlaceList.add(p0);

        DataSubPetriNet p2 = new DataSubPetriNet();
        p2.SetName("p2");
        pn.PlaceList.add(p2);

        DataFloat p3 = new DataFloat();
        p3.SetName("p3");
        pn.PlaceList.add(p3);

        // TRANSITIONS
        PetriTransition t0 = new PetriTransition(pn);
        t0.TransitionName = "t0";
        t0.InputPlaceName.add("p0");
        t0.InputPlaceName.add("p3");
        Condition T0Ct1 = new Condition(t0, "p0", TransitionCondition.NotNull);
        Condition T0Ct2 = new Condition(t0, "p3", TransitionCondition.NotNull);
//        Condition T0Ct2 = new Condition(t0, "p4", TransitionCondition.LessThanOrEqual, "constantValue1");
        T0Ct1.SetNextCondition(LogicConnector.AND, T0Ct2);
        GuardMapping grdT0 = new GuardMapping();
        grdT0.condition = T0Ct1;
        grdT0.Activations.add(new Activation(t0, "SubPetri", TransitionOperation.Move, "p1"));
        grdT0.Activations.add(new Activation(t0, "p3", TransitionOperation.Move, "p1-p8"));
        grdT0.Activations.add(new Activation(t0, "p1", TransitionOperation.ActivateSubPetri, "")); // Activare Sub Petri Net Din P1
        t0.GuardMappingList.add(grdT0);

        // ALT GUARD PENTRU T0
//        Condition T1Ct3 = new Condition(t1, "p1", TransitionCondition.NotNull);
//        Condition T1Ct4 = new Condition(t1, "p4", TransitionCondition.MoreThan, "constantValue1");
//        T1Ct3.SetNextCondition(LogicConnector.AND, T1Ct4);
//        GuardMapping grd2T1 = new GuardMapping();
//        grd2T1.condition = T1Ct3;
//        grd2T1.Activations.add(new Activation(t1, "SubPetri", TransitionOperation.Copy, "p2"));
//        grd2T1.Activations.add(new Activation(t1, "p4", TransitionOperation.Move, "p2-p21"));
//        t1.GuardMappingList.add(grd2T1);


        t0.Delay = 0;
        pn.Transitions.add(t0);

        // T2 ------------------------------------------------
        PetriTransition t2 = new PetriTransition(pn);
        t2.TransitionName = "t2";
        t2.InputPlaceName.add("p2");
        Condition T2Ct1 = new Condition(t2, "p2", TransitionCondition.NotNull);
        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition = T0Ct1;
        grdT2.Activations.add(new Activation(t2, "p2", TransitionOperation.Move, "p0"));
        t2.GuardMappingList.add(grdT2);
        t2.Delay = 3000;
        pn.Transitions.add(t2);

        System.out.println("Exp1 started \n ------------------------------");
        pn.Delay = 3000;

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);
    }
}