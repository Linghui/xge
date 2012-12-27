#!/usr/bin/perl -w
# for generate XPanel children

use strict;
use warnings;

use GJava;
use JSON;
use Getopt::Std;

my $class_output_dir = 'classes/';

my %opts = ();

# options
# h: help
# f: for file or stdin input
# none: for command line string input
getopts('hfo:', \%opts);

if( $opts{h} ){
    &usage();
    exit 0;
}

if( $opts{o} ){
    $class_output_dir = $opts{o};
    exit 0;
}


my $input = undef;

if( $opts{f} ){
    my @all_lines = <>;
    chomp @all_lines;
    $input = join '', @all_lines;
} else {
    
    if(!defined($ARGV[0])){
        print STDERR "Need json config string input\n";
        &usage();
        exit 1;
    }
    $input = $ARGV[0];
}

my $NAME_KEY = 'name';
my $TYPE_KEY = 'type';
my $PANEL_TYPE = 0;

my $config = from_json($input);
my $type = $config->{$TYPE_KEY};

my $gJava = GJava->new();
my ( $class, $action_class ) ;

if( $type == $PANEL_TYPE){
    ($class, $action_class) = $gJava->generate_panel_table($config);
}


open WRT, "> $class_output_dir".$config->{$NAME_KEY}.".java"

or die "open file error $!";

print WRT "$class";

close WRT;


open WRT, "> $class_output_dir".$config->{$NAME_KEY}."Actions.java"

or die "open file error $!";

print WRT "$action_class";

close WRT;

sub usage(){
    
    print <<EOF;
Usage:
    perl $0 [-h | -f \$file_name/STDIN | \$json_string]
    -h: help
    -f: file or STDIN input mode
example: perl $0 -f config_demo.json
    no options: for command json string input
        
EOF
}

sub HELP_MESSAGE(){
    &usage();
    exit 0;
}
