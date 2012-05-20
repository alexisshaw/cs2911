package Game.testadapter;

import card.Buildings.*;
import card.Character.*;
import card.GrimReaperCard;
import card.KatCard;
import card.TelephoneBoxCard;
import framework.cards.Card;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Alexis Shaw
 * Date: 5/20/12
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class AssetTranslator {
    static public framework.cards.Card fromInternalToTestCard(card.Card c){
        if(c.getClass() == AesculapinumCard.class){
            return Card.AESCULAPINUM;
        } else if(c.getClass() == ArchitectusCard.class){
            return Card.ARCHITECTUS;
        } else if(c.getClass() == BasilicaCard.class){
            return Card.BASILICA;
        } else if(c.getClass() == CenturioCard.class){
            return Card.CENTURIO;
        } else if(c.getClass() == ConsiliariusCard.class){
            return Card.CONSILIARIUS;
        } else if(c.getClass() == ConsulCard.class){
            return Card.CONSUL;
        } else if(c.getClass() == EssedumCard.class){
            return Card.ESSEDUM;
        } else if(c.getClass() == ForumCard.class){
            return Card.FORUM;
        } else if(c.getClass() == GladiatorCard.class){
            return Card.GLADIATOR;
        } else if(c.getClass() == HaruspexCard.class){
            return Card.HARUSPEX;
        } else if(c.getClass() == LegatCard.class){
            return Card.LEGAT;
        } else if(c.getClass() == LegionariusCard.class){
            return Card.LEGIONARIUS;
        } else if(c.getClass() == MachinaCard.class){
            return Card.MACHINA;
        } else if(c.getClass() == MercatorCard.class){
            return Card.MERCATOR;
        } else if(c.getClass() == MercatusCard.class){
            return Card.MERCATUS;
        } else if(c.getClass() == NeroCard.class){
            return Card.NERO;
        } else if(c.getClass() == OnagerCard.class){
            return Card.ONAGER;
        } else if(c.getClass() == PraetorianusCard.class){
            return Card.PRAETORIANUS;
        } else if(c.getClass() == ScaenicusCard.class){
            return Card.SCAENICUS;
        } else if(c.getClass() == SenatorCard.class){
            return Card.SENATOR;
        } else if(c.getClass() == SicariusCard.class){
            return Card.SICARIUS;
        } else if(c.getClass() == TempulmCard.class){
            return Card.TEMPLUM;
        } else if(c.getClass() == TribunusPlebusCard.class){
            return Card.TRIBUNUSPLEBIS;
        } else if(c.getClass() == TurrisCard.class){
            return Card.TURRIS;
        } else if(c.getClass() == VelitesCard.class){
            return Card.VELITES;
        } else if(c.getClass() == GrimReaperCard.class){
            return Card.GRIMREAPER;
        } else if(c.getClass() == KatCard.class){
            return Card.KAT;
        } else if(c.getClass() == TelephoneBoxCard.class){
            return Card.TELEPHONEBOX;
        } else {
            return Card.NOT_A_CARD;
        }
    }
    static public card.Card fromTestToInternalCard(framework.cards.Card c){
        card.Card toReturn;
        switch (c) {
            case AESCULAPINUM:
                toReturn = new AesculapinumCard();
                break;
            case ARCHITECTUS:
                toReturn = new ArchitectusCard();
                break;
            case BASILICA:
                toReturn = new BasilicaCard();
                break;
            case CENTURIO:
                toReturn = new CenturioCard();
                break;
            case CONSILIARIUS:
                toReturn = new ConsiliariusCard();
                break;
            case CONSUL:
                toReturn = new ConsulCard();
                break;
            case ESSEDUM:
                toReturn = new EssedumCard();
                break;
            case FORUM:
                toReturn = new ForumCard();
                break;
            case GLADIATOR:
                toReturn = new GladiatorCard();
                break;
            case HARUSPEX:
                toReturn = new HaruspexCard();
                break;
            case LEGAT:
                toReturn = new LegatCard();
                break;
            case LEGIONARIUS:
                toReturn = new LegionariusCard();
                break;
            case MACHINA:
                toReturn = new MachinaCard();
                break;
            case MERCATOR:
                toReturn = new MercatorCard();
                break;
            case MERCATUS:
                toReturn = new MercatusCard();
                break;
            case NERO:
                toReturn = new NeroCard();
                break;
            case ONAGER:
                toReturn = new OnagerCard();
                break;
            case PRAETORIANUS:
                toReturn = new PraetorianusCard();
                break;
            case SCAENICUS:
                toReturn = new ScaenicusCard();
                break;
            case SENATOR:
                toReturn = new SenatorCard();
                break;
            case SICARIUS:
                toReturn = new SicariusCard();
                break;
            case TEMPLUM:
                toReturn = new TempulmCard();
                break;
            case TRIBUNUSPLEBIS:
                toReturn = new TribunusPlebusCard();
                break;
            case TURRIS:
                toReturn = new TurrisCard();
                break;
            case VELITES:
                toReturn = new VelitesCard();
                break;
            case GRIMREAPER:
                toReturn = new GrimReaperCard();
                break;
            case KAT:
                toReturn = new KatCard();
                break;
            case TELEPHONEBOX:
                toReturn = new TelephoneBoxCard();
                break;
            case NOT_A_CARD:
                toReturn = null;
                break;
            default:
                toReturn = null;
                break;
        }
        return toReturn;
    }
    static public card.Card findEquivelentCard(Collection<card.Card> toLookIn, framework.cards.Card toLookFor){
        Class<? extends card.Card> cardClass = fromTestToInternalCard(toLookFor).getClass();
        card.Card toReturn = null;
        for(card.Card c: toLookIn) if(c.getClass() == cardClass)
            toReturn = c;
        return toReturn;
    }
    static public Game.Die  findEquivelentDie (Collection<Game.Die>  toLookIn, int toLookFor){
          for(Game.Die d: toLookIn) if(d.getDieValue() == toLookFor) return d;
          return null;
    }
}
