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

my $IMAGE_BUTTONS_KEY   =   'image_buttons';
my $IMAGE_BUTTONS_DOWN_REGION_KEY   =   'down_region';
my $IMAGE_BUTTONS_UP_REGION_KEY     =   'up_region';

my $GCTXC = GCTXC->new();

my $scene_config = {
    $SCENE_NAME_KEY => 'TestScreen',
    $BACKGROUND_IMG_KEY =>'data/libgdx.png',
    $SKIN_KEY   =>  'data/uiskin.json',
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
    
};

#my $config_config = $GCTXC->convert_to_java($scene_config);

my $gJava = GJava->new();
$gJava->generate_java($scene_config);
