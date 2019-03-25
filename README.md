# prioritySystem
The priority system for our competition.

from elf_kingdom import *

elf = None
elf2 = None
destroy_flag = False
angry_bird_flag = False
mini_mana_flag = False
mana_fondue_flag = False

#p_l = [Location()]


def destroy_s(game):
    if len(game.get_enemy_mana_fountains()) != 0:
        if elf.in_attack_range(game.get_enemy_mana_fountains()[0]):
            elf.attack(game.get_enemy_mana_fountains()[0])
        else:
            elf.move_to(game.get_enemy_mana_fountains()[0])
    else:
        if elf.in_attack_range(game.get_enemy_castle()):
            elf.attack(game.get_enemy_castle())
        else:
            elf.move_to(game.get_enemy_castle())
            
def angry_bird(game):
    
    if len(game.get_enemy_living_elves()) != 0:
        if len(game.get_enemy_portals()) != 0:
            if elf.in_attack_range(game.get_enemy_portals()[0]):
                elf.attack(game.get_enemy_portals()[0])
            else:
                elf.move_to(game.get_enemy_portals()[0])
        else:
            if elf.in_attack_range(game.get_enemy_living_elves()[0]):
               elf.attack(game.get_enemy_living_elves()[0])
            else:
                elf.move_to(game.get_enemy_living_elves()[0])
                
        
    if elf2.get_location().equals(Location(2542,1429)):
        if elf2.can_build_portal():
            elf2.build_portal()
    else:
        elf2.move_to(Location(2542,1429))
        
    for p in game.get_my_portals():
        p.summon_lava_giant()

def mini_mana(game):
    if len(game.get_my_mana_fountains()) == 0:
        
        if elf.get_location().equals(Location(1606,5794)):
            elf.build_mana_fountain()
        else:
            elf.move_to(Location(1606,5794))
    else:
        if elf.get_location().equals(Location(1507,1182)):
            if elf.can_build_portal():
                elf.build_portal()
        else:
            elf.move_to(Location(1507,1182))
            
        
        for p in game.get_my_portals():
            if p.can_summon_lava_giant:
                p.summon_lava_giant()
                
    if len(game.get_enemy_portals()) != 0:
            if elf2.in_attack_range(game.get_enemy_portals()[0]):
                elf2.attack(game.get_enemy_portals()[0])
            else:
                elf2.move_to(game.get_enemy_portals()[0])
        
def mana_fondue(game):
    None

def do_turn(game):
    global destroy_flag, angry_bird_flag, mini_mana_flag, mana_fondue_flag
    global elf, elf2
    
    if game.turn == 1:
        elf = game.get_my_living_elves()[0]
        
        try:
            elf2 = game.get_my_living_elves()[1]
            print elf2
        except:
            print('eeee')
            
            
            
        if game.get_enemy_castle().current_health == 75:
            destroy_flag = True
        elif game.get_my_mana() == 255 :
            angry_bird_flag = True
        elif game.get_my_mana() == 200:
            mini_mana_flag = True
        elif game.get_my_mana() == 125:
            mana_fondue_flag = True
    
    
    
    if destroy_flag:
        destroy_s(game)
    elif angry_bird_flag:
        angry_bird(game)
    elif mini_mana_flag:
        mini_mana(game)
    elif mana_fondue_flag:
        mana_fondue(game)
    
        
        
        
        
    
