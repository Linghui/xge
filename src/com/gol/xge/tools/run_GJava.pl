#!/usr/bin/perl -w

use strict;
use warnings;

use GCTXC;
use GJava;

my $SCENE_NAME_KEY  = 'scene_name';
my $BACKGROUND_IMG_KEY  = 'background';
my $SKIN_KEY    =   'skin';
my $TEXT_BUTTONS_KEY    =   'text_buttons';
my $TEXT_KEY    =   'text';
my $X_KEY   =   'x';
my $Y_KEY   =   'y';

my $LABELS_KEY  =   'labels';
my $STYLE_KEY   =   'style';

my $IMAGE_BUTTONS_KEY   =   'image_buttons';
my $IMAGE_BUTTONS_DOWN_REGION_KEY   =   'down_region';
my $IMAGE_BUTTONS_UP_REGION_KEY     =   'up_region';

my $LOADING_BACKGROUND_KEY  =   'loading_backgroud';
my $LADING_BAR_KEY  =   'loading_bar';
my $BAR_TOP     =   'bar_top';
my $BAR_BOTTOM  =   'bar_bottom';
my $BAR_WIDTH   =   'bar_width';
my $BAR_HEIGHT  =   'bar_height';
my $BAR_X   =   'bar_x';
my $BAR_Y   =   'bar_y';

my $LEADING_ROLE_SRC    =   'leading_role_src';

my $NPCS_KEY    =   'npcs';
my $NPC_NAME    =   'npc_name';
my $NPC_SRC     =   'npc_src';


my $GCTXC = GCTXC->new();

my $scene_config = {
    $SCENE_NAME_KEY => 'TestScreen',
    $LOADING_BACKGROUND_KEY =>  'data/40009002_pack1.png',
    $LADING_BAR_KEY =>  {
        $BAR_TOP    =>  'default-round',
        $BAR_BOTTOM =>  'default-round',
        $BAR_WIDTH  =>  '300',
        $BAR_HEIGHT =>  '50',
        $BAR_X      =>  '50',
        $BAR_Y      =>  '50',
        
    },
    
    $BACKGROUND_IMG_KEY =>'data/pack1.png',
    $SKIN_KEY   =>  'data/uiskin.json',
    
    $LABELS_KEY =>  {
        'l1'  =>{
            $TEXT_KEY     =>  'success',
            $STYLE_KEY    =>  'default',
            $X_KEY        =>  600,
            $Y_KEY        =>  200,
        }
    },
    
    $TEXT_BUTTONS_KEY   =>  {
        'btn1'  =>  {
            $TEXT_KEY   =>  'success',
            $X_KEY  =>  100,
            $Y_KEY  =>  100,
            
        },
        'btn2'  =>  {
            $TEXT_KEY   =>  'very well',
            $X_KEY  =>  150,
            $Y_KEY  =>  100,
            
        }
    },
    $IMAGE_BUTTONS_KEY  =>  {
        'image1'  =>  {
            $TEXT_KEY   =>  'success',
            $X_KEY  =>  200,
            $Y_KEY  =>  200,
            $IMAGE_BUTTONS_DOWN_REGION_KEY  =>  'qishu',
            $IMAGE_BUTTONS_UP_REGION_KEY    =>  'bag',
        },
    },
    
    $LEADING_ROLE_SRC   =>  'data/30100001',
    
    $NPCS_KEY    =>  {
        'cuihua'  =>  {
            $NPC_NAME   =>  '翠花',
            $NPC_SRC    =>  'data/30100001',
            $X_KEY      =>  '300',
            $Y_KEY      =>  '400',
        },
        'taishan'  =>  {
            $NPC_NAME   =>  '泰山',
            $NPC_SRC    =>  'data/30100002',
            $X_KEY      =>  '300',
            $Y_KEY      =>  '450',
        },
        'kezhan'  =>  {
            $NPC_NAME   =>  '客栈',
            $NPC_SRC    =>  'data/30100003',
            $X_KEY      =>  '200',
            $Y_KEY      =>  '300',
        },
        'shanpao'  =>  {
            $NPC_NAME   =>  '山炮',
            $NPC_SRC    =>  'data/30100004',
            $X_KEY      =>  '450',
            $Y_KEY      =>  '300',
        },
        'biesan'  =>  {
            $NPC_NAME   =>  '瘪三',
            $NPC_SRC    =>  'data/30100005',
            $X_KEY      =>  '100',
            $Y_KEY      =>  '300',
        },
        'dana'  =>  {
            $NPC_NAME   =>  '大拿',
            $NPC_SRC    =>  'data/30100006',
            $X_KEY      =>  '600',
            $Y_KEY      =>  '300',
        },
        'dasheng'  =>  {
            $NPC_NAME   =>  '大圣',
            $NPC_SRC    =>  'data/30100008',
            $X_KEY      =>  '500',
            $Y_KEY      =>  '300',
        },
        'zhu'  =>  {
            $NPC_NAME   =>  '猪',
            $NPC_SRC    =>  'data/30100009',
            $X_KEY      =>  '200',
            $Y_KEY      =>  '200',
        },
        'lujia'  =>  {
            $NPC_NAME   =>  '路人甲',
            $NPC_SRC    =>  'data/30100010',
            $X_KEY      =>  '100',
            $Y_KEY      =>  '100',
        },
        'jiong'  =>  {
            $NPC_NAME   =>  '囧人',
            $NPC_SRC    =>  'data/30100011',
            $X_KEY      =>  '300',
            $Y_KEY      =>  '150',
        },
        'shangdi'  =>  {
            $NPC_NAME   =>  'God',
            $NPC_SRC    =>  'data/30100012',
            $X_KEY      =>  '150',
            $Y_KEY      =>  '150',
        },
    },
};

#my $config_config = $GCTXC->convert_to_java($scene_config);

my $gJava = GJava->new();
$gJava->generate_java($scene_config);
