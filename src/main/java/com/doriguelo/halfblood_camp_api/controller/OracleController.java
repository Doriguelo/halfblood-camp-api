package com.doriguelo.halfblood_camp_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/oracle")
public class OracleController {

    private final List<String> prophecies = List.of(
            "You shall go west, and face the god who has turned.You shall find what was stolen, and see it safely returned.You shall be betrayed by one who calls you a friend.And you shall fail to save what matters most, in the end.",
            "You shall sail the iron ship with the warriors of bone,You shall find what you seek and make it your own,But despair for your life entombed within stone,And fail without friends, to fly home alone.",
            "Five shall go west to the goddess in chains,One shall be lost in the land without rain,The bane of Olympus shows the trail,Campers and Hunters combined prevail,The Titan’s curse must one withstand,And one shall perish by a parent’s hand",
            "You shall delve in the darkness of the endless maze,The dead, the traitor, and the lost one raise.You shall rise or fall by the ghost king’s hand,The Child of Athena’s final stand.Destroy with a hero’s final breath,And lose a love to worse than death.",
            "A Half-Blood of the eldest gods,Shall reach sixteen against all odds,And see the world in endless sleep.The hero’s soul, cursed blade shall reap,A single choice shall end his days,Olympus to preserve or raze.",
            "Seven half-bloods shall answer the call.To storm or fire, the world must fall.An oath to keep with a final breath,And foes bear arms to the Doors of Death",
            "Child of Lightning, beware the earth,The giants’ revenge the seven shall birth,The forge and dove shall break the cage,And death unleash through Hera’s rage.",
            "Go to Alaska.Find Thanatos and free him.Come back by sundown on June twenty-fourth or die.",
            "To the north, beyond the gods, lies the legion’s crown.Falling from ice, the son of Neptune shall drown.",
            "Wisdom’s daughter walks alone,The Mark of Athena burns through Rome.Twins snuff out the angel’s breath,Who holds the key to endless death.Giants’ bane stands gold and pale,Won through pain from a woven jail.",
            "The fall of the sun, the final verse.",
            "Caves of blue.Strike the hue.Westward, burning.Pages turning.Indiana.Ripe banana.Happiness approaches.Serpents and roaches.",
            "There once was a god named ApolloWho plunged in a cave, blue and hollowUpon a three-seaterThe bronze fire-eaterWas forced death and madness to swallow",
            "The words that memory wrought are set to fire,Ere new moon rises o’re the Devil’s Mount.The changeling lord shall face a challenge dire,Till bodies fill the Tiber beyond count.Yet southward now the sun must trace its course,Through mazes dark to lands of scorching deathTo find the master of the swift white horseAnd wrest from him the crossword speaker’s breath.To westward palace must the Lester go;Demeter’s daughter finds her ancient roots.The cloven guide alone the way does know,To walk the path in thine own enemy’s boots.When three are known and Tiber reached alive,‘Tis only then Apollo starts to jive.",
            "A wildcat near the spinning lights,The tomb of Tarquin with horses bright.To open his door,Two-fifty-four.",
            "O son of Zeus the final challenge faceThe tow’r of Nero two alone ascendDislodge the beast that hast usurped thy placeThe son of Hades, cavern-runner’s friend,Must show the secret way unto the throne.On Nero’s own your lives now depend.A Dare reveals the path that was unknownAnd bears destruction; lion, snake-entwinedOr else the princeps never be o’erthrownApollo’s flesh and blood shall soon be mineAlone he must descend into the darkThe Sibyl never again to see his signLest wrestle with me till see his final sparkThe god dissolves, Leaving not a mark",
            "When sea and sky in silence clash,A loyal heart will choose at last.What seems a loss shall break the chain,And hope will rise from godly pain.",
            "An oath once sworn beneath the flame,Will doom a friend or crown a name.Trust the blade that dares to bend,For traitors fall where vows must end.",
            "From halls ignored, a power wakes,Born of laughter, lies, and stakes.The child unseen shall tilt the war,And close a long-neglected door.",
            "At sixteen’s edge, with world in sight,One choice shall seal the gods’ true might.The hand that spares will lose its throne,Yet save a fate not meant alone.",
            "When monsters fall like autumn rain,And heroes bleed on mortal plain,The final wave will not destroy,But wash the past and leave a choice.",
            "Where oceans rage and heavens break,The sea-born son a stand must take.To save the many, one must fall,Yet mercy weighs the most of all.",
            "By wit, not blade, the path is drawn,Through shattered pride and wisdom’s dawn.The mind that builds shall face its test,To lose the heart to save the rest.",
            "Born of flame and broken fate,The fire-born walks alone through hate.To end the war, the spark must die,Yet death itself shall pass him by."
    );

    @GetMapping("/prophecy")
    public String consultOracle() {
        Random random = new Random();
        String chosenProphecy = prophecies.get(random.nextInt(prophecies.size()));
        return "--- THE GREEN MIST SWIRLS AND THE ORACLE SPEAKS ---\n" +
                "\"" + chosenProphecy + "\"";
    }
}
