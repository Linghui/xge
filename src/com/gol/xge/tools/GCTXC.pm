#!/usr/bin/perl -w
# GCTXC is game config to X(language) config.
#   X now is only Java,
#   but maybe in the future it can be more, like objective-C or html5

package GCTXC;


my $CLASS_NAME      = 'class_name';
my $EXTENDS_KEY     = 'extends';
my $IMPLEMENTS_KEY  = 'implements';

my $SCENE_NAME_KEY  = 'scene_name';
my $BACKGROUND_IMG_KEY  = 'background';
my $SKIN_KEY    =   'skin';
my $SOURCE_LOADING  = 'source_loading';

my $TEXT_BUTTONS_KEY    =   'text_buttons';
my $TEXT_KEY    =   'text';
my $X_KEY   =   'x';
my $Y_KEY   =   'y';

use strict;
use warnings;
use Data::Dumper qw/Dumper/;


sub new(){
    my $class = shift;
    my $self = {};
    bless $self, $class;
    return $self;
}

sub convert_to_java(){
    
    my $self = shift;
    my $scene_config = shift;
    
    my $scene_parent = 'RPGScreen';
    
#    my $scene_interface = 'Screen';
    
    my $java_config = {
        $CLASS_NAME     => $scene_config->{$SCENE_NAME_KEY},
        $EXTENDS_KEY    => $scene_parent,
        $BACKGROUND_IMG_KEY => $scene_config->{$BACKGROUND_IMG_KEY},
        $SOURCE_LOADING => [$scene_config->{$BACKGROUND_IMG_KEY}, $scene_config->{$SKIN_KEY}],
        $SKIN_KEY     => $scene_config->{$SKIN_KEY},
        $TEXT_BUTTONS_KEY     => $scene_config->{$TEXT_BUTTONS_KEY},
    };
    
    return $java_config;
    
}

1

